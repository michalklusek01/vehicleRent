package pl.klusek.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klusek.michal.dao.IUserDAO;
import pl.klusek.michal.model.view.Mail;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.services.impl.VehicleService;
import pl.klusek.michal.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasicController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    VehicleService vehicleService;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IReservationService reservationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("vehicles", this.vehicleService.getFilteredVehicles());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main2(Model model){
        return "redirect:/";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("mail", new Mail());
        return "contact";
    }
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String processForm(@ModelAttribute Mail mail) {
        System.out.println(mail.getTitle());
        System.out.println(mail.getMessage());
        System.out.println(mail.getName());
        return "redirect:/";
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find (@RequestParam String pattern){
        this.sessionObject.setFindPattern(pattern);;
        return "redirect:/";
    }

    @RequestMapping(value = "/filterVehicles", method = RequestMethod.POST)
    public String filterVehicles(@RequestParam String vehicle_type){
        this.sessionObject.setVehicleTypeFilter(vehicle_type);
        return "redirect:/";
    }

    @RequestMapping(value = "/filterTransmission", method = RequestMethod.POST)
    public String filterTransmission(@RequestParam String transmission_type){
        this.sessionObject.setTransmissionTypeFilter(transmission_type);
        return "redirect:/";
    }

}
