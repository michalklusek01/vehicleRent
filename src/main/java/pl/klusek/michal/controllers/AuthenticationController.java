package pl.klusek.michal.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klusek.michal.model.Motorcycle;
import pl.klusek.michal.model.User;
import pl.klusek.michal.services.impl.AuthenticationService;
import pl.klusek.michal.session.SessionObject;
import pl.klusek.michal.validators.LoginValidator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.getUser() != null);
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST) //tutaj był link /login
    public String login2(@RequestParam String login, @RequestParam String password) {
        if(!LoginValidator.validateLogin(login) || !LoginValidator.validatePassword(password)){
            this.sessionObject.setInfo("Logowanie nieudane!");
            return "redirect:/main";
        }

        if(authenticationService.authenticate(login, password)){
            this.sessionObject.setFindPattern("");
            return "redirect:/main";
        } else {
            this.sessionObject.setInfo("Logowanie nieudane! Nie ma takiego użytkownika!");
            return "redirect:/main";
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user){
        this.authenticationService.registerUser(user);

        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.logout();
        this.sessionObject.setFindPattern("");
        return "redirect:/";
    }

    @RequestMapping(value="/myAccount", method = RequestMethod.GET)
    public String myAccount(Model model){
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.getUser() != null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        model.addAttribute("userAccount", this.sessionObject.getUser() != null ? this.sessionObject.getUser() : null);

        return "myAccount";
    }

    @RequestMapping(value = "/myAccount", method = RequestMethod.POST)
    public String changePassword(@RequestParam String password){
        User user = this.sessionObject.getUser();
        if(password.equals("") || DigestUtils.md5Hex(password).equals(this.sessionObject.getUser().getPassword())){
            this.sessionObject.setInfo("Hasło nieprawidłowe!");
        }else if(!LoginValidator.validatePassword(password)){
            this.sessionObject.setInfo("Hasło za krótkie! Musi posiadać min. 3 znaki");
        }else{
            user.setPassword(DigestUtils.md5Hex(password));
            this.authenticationService.updateUserPassword(user);
            return "redirect:/main";
        }
        return "redirect:/myAccount";
    }

    @GetMapping("/passwordReminder")
    public String passwordReminder(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "passwordReminder";
    }

    @PostMapping("/passwordReminder")
    public String passwordReminder2(@ModelAttribute User user){
        if(this.authenticationService.getUserByLogin(user.getLogin()) == null){
            this.sessionObject.setInfo("Błędne dane!");
            return "redirect:/passwordReminder";
        }

        if(this.authenticationService.checkUserData(user) &&  LoginValidator.validatePassword(user.getPassword())){
            User userFromDb = this.authenticationService.getUserByLogin(user.getLogin());
            userFromDb.setPassword(DigestUtils.md5Hex(user.getPassword()));
            this.authenticationService.updateUserPassword(userFromDb);
            this.sessionObject.setInfo("Hasło zmienione!!!");
        }else{
            this.sessionObject.setInfo("Nieprawidłowe dane!!");
            return "redirect:/passwordReminder";
        }
        return "redirect:/";

    }
}
