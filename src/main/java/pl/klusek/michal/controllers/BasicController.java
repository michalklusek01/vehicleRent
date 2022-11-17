package pl.klusek.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klusek.michal.dao.IUserDAO;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.services.impl.VehicleService;
import pl.klusek.michal.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main2(Model model){
        return "redirect:/";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String find (@RequestParam String pattern){
        this.sessionObject.setFindPattern(pattern);
        return "redirect:/";
    }

    @RequestMapping(value = "/rent_date", method = RequestMethod.POST)
    public String rentDate (@RequestParam String dateString1, @RequestParam String dateString2, @RequestParam String brand, @RequestParam String model, @RequestParam String licensePlate){
        LocalDate date1 = LocalDate.parse(dateString1);
        LocalDate date2 = LocalDate.parse(dateString2);
        System.out.println(brand);
        System.out.println(model);
        System.out.println(licensePlate);
        System.out.println(date1);
        System.out.println(date2);
        this.reservationService.addReservation(this.sessionObject.getUser(), this.vehicleService.getVehicleByData(brand,model,licensePlate), date1, date2);

        return "redirect:/";
        //TODO
        //zrobić sprawdzenie czy samochod w danym temrinie nie jest zajety
        //zrobic informacje na stronie porownujace date dzisiejsza z data czy samochod nie jest zarezerwowany
        //zrobic widok rezerwacji
        //zrobic w widoku rezerwacji anulacje
        //dodac koszt do rezerwacji (dni*koszt wynajmu)
        //przekminic jak dodac informacje z odpowiendich tabel do pojazdów (Car, Bike, Motorcycle)
        //Przeniesc rezerwacje do nowego widoku (do zastanowienia ?)
        //porobic odpowiednie Controllery
    }


}
