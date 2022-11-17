package pl.klusek.michal.controllers;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login2(@RequestParam String login, @RequestParam String password) {
        if(!LoginValidator.validateLogin(login) || !LoginValidator.validatePassword(password)){
            this.sessionObject.setInfo("Logowanie nieudane!");
            return "redirect:/login";
        }

        if(authenticationService.authenticate(login, password)){
            this.sessionObject.setFindPattern("");
            return "redirect:/main";
        } else {
            this.sessionObject.setInfo("Logowanie nieudane! Nie ma takiego użytkownika!");
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        //wysylamy do formularza nowy pusty obiekt User
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user){
        //powiazalismy w html pola formularza z polami  nowego Usera
        //i wysylamy teraz uzupelnionego do metody
        this.authenticationService.registerUser(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.logout();
        this.sessionObject.setFindPattern("");
        return "redirect:/";
    }

}
