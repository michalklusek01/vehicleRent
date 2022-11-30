package pl.klusek.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.services.IVehicleService;
import pl.klusek.michal.session.SessionObject;

import java.time.LocalDate;

@Controller
public class ReservationController {
    @Autowired
    SessionObject sessionObject;

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    IReservationService reservationService;

    @RequestMapping(value = "/rent_date", method = RequestMethod.POST)
    public String rentDate (@RequestParam String dateString1, @RequestParam String dateString2, @RequestParam String licensePlate){
        LocalDate date1 = LocalDate.parse(dateString1);
        LocalDate date2 = LocalDate.parse(dateString2);
        this.reservationService.addReservation(this.sessionObject.getUser(), this.vehicleService.getVehicleByLicensePlate(licensePlate), date1, date2);

        return "redirect:/";
        //TODO
        //zrobić sprawdzenie czy samochod w danym temrinie nie jest zajety ----OGARNIĘTE----
        //zrobic informacje na stronie porownujace date dzisiejsza z data czy samochod nie jest zarezerwowany ---NIEPOTRZEBNE---
        //zrobic dodawanie pojazdów przez Admin --OGARNIETE--
        //zrobic edycje pojazdów przez Admina --OGARNIETE--
        //zrobic edycje hasla dla uzytkownikow //TODO
        //zrobić filtrowanie na ekranie // --OGARNIETE PO TYPIE POJAZDU-- //TODO
        //zrobic widok rezerwacji ----OGARNIĘTE----
        //zrobic w widoku rezerwacji anulacje //TODO
        //dodac koszt do rezerwacji (dni*koszt wynajmu) ----OGARNIĘTE----
        //przekminic jak dodac informacje z odpowiendich tabel do pojazdów (Car, Bike, Motorcycle) ----OGARNIĘTE----
        //Przeniesc rezerwacje do nowego widoku (do zastanowienia ?)
        //porobic odpowiednie Controllery --OGARNIETE--
        //biblioteka bootstrap do fronta dla poprawy wyglądu strony //TODO
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(Model model){
        model.addAttribute("info", this.sessionObject.getInfo()); //html wyswietli jesli info jest rożne od null
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);

        model.addAttribute("reservations", this.reservationService.getReservationsByUserId(this.sessionObject.getUser().getId()));

        return "reservations";
    }
}
