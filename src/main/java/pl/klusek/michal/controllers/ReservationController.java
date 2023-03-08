package pl.klusek.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klusek.michal.model.Reservation;
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
    public String rentDate (@RequestParam String dateString1,
                            @RequestParam String dateString2,
                            @RequestParam String licensePlate){
        LocalDate date1 = LocalDate.parse(dateString1);
        LocalDate date2 = LocalDate.parse(dateString2);
        this.reservationService.addReservation(this.sessionObject.getUser(),
                this.vehicleService.getVehicleByLicensePlate(licensePlate), date1, date2);

        return "redirect:/";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(Model model){
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        model.addAttribute("reservations", this.reservationService.getReservationsByUserId(this.sessionObject.getUser().getId()));

        return "reservations";
    }

    @RequestMapping(value = "reservations/cancel/{reservationId}", method = RequestMethod.GET)
    public String reservationCancel(@PathVariable String reservationId){
        int id = Integer.parseInt(reservationId);
        Reservation reservationFromDb = this.reservationService.getReservationById(id);
        this.reservationService.deleteReservation(reservationFromDb);
        this.sessionObject.setInfo("Rezerwacja " + reservationFromDb.getId() + " usunięta z bazy danych!!!");
        return "redirect:/main";
    }
}
