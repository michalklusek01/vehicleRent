package pl.klusek.michal.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Car;
import pl.klusek.michal.model.Motorcycle;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IBikeService;
import pl.klusek.michal.services.ICarService;
import pl.klusek.michal.services.IMotorcycleService;
import pl.klusek.michal.services.IVehicleService;
import pl.klusek.michal.session.SessionObject;

@Controller
public class VehicleController {
    @Autowired
    SessionObject sessionObject;
    @Autowired
    ICarService carService;
    @Autowired
    IMotorcycleService motorcycleService;
    @Autowired
    IBikeService bikeService;
    @Autowired
    IVehicleService vehicleService;

    @RequestMapping(value = "addVehicle", method = RequestMethod.GET)
    public String addVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "addVehicle";
    }

    @RequestMapping(value = "addCar", method = RequestMethod.GET)
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "addCar";
    }

    @RequestMapping(value = "addCar", method = RequestMethod.POST)
    public String addCar2(@ModelAttribute Car car){
        if(this.carService.addCar(car)){
            return "redirect:/addCar";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "addMotorcycle", method = RequestMethod.GET)
    public String addMotorcycle(Model model){
        model.addAttribute("motorcycle", new Motorcycle());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "addMotorcycle";
    }

    @RequestMapping(value = "addMotorcycle", method = RequestMethod.POST)
    public String addMotorcycle2(@ModelAttribute Motorcycle motorcycle){
        if(this.motorcycleService.addMotorcycle(motorcycle)){
            return "redirect:/addMotorcycle";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "addBike", method = RequestMethod.GET)
    public String addBike(Model model){
        model.addAttribute("bike", new Bike());
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "addBike";
    }

    @RequestMapping(value = "addBike", method = RequestMethod.POST)
    public String addBike2(@ModelAttribute Bike bike){
        if(this.bikeService.addBike(bike)){
            return "redirect:/addBike";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/editVehicle/{licensePlate}", method = RequestMethod.GET)
    public String editVehicle(Model model, @PathVariable String licensePlate){
        Vehicle vehicle1 = this.vehicleService.getVehicleByLicensePlate(licensePlate);
        if(vehicle1 == null){
            return "redirect:/";
        }
        model.addAttribute("vehicle", vehicle1);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("user", this.sessionObject.getUser() != null ? this.sessionObject.getUser().getLogin() : null);
        return "editVehicle";
    }

    @RequestMapping(value = "/editVehicle/{licensePlate}", method = RequestMethod.POST)
    public String editVehicle2(@PathVariable String licensePlate, @ModelAttribute Vehicle vehicle){
        Vehicle vehicleFromDb = this.vehicleService.getVehicleByLicensePlate(licensePlate);
        if(this.vehicleService.updateVehicle(vehicleFromDb.getLicensePlate(), vehicle)){
            return "redirect:/main";
        }

        return "redirect:/editVehicle/" + licensePlate;
    }

    @RequestMapping(value = "/deleteVehicle/{licensePlate}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable String licensePlate){
        Vehicle vehicleFromDb = this.vehicleService.getVehicleByLicensePlate(licensePlate);
        if(vehicleFromDb == null){
            this.sessionObject.setInfo("Nie ma takiego pojazdu w bazie danych");
            return "redirect:/main";
        }
        this.vehicleService.deleteVehicle(vehicleFromDb);
        this.sessionObject.setInfo("Pojazd " + vehicleFromDb.getBrand()
                + " " + vehicleFromDb.getModel() + " usunięty z bazy danych!!!");
        return "redirect:/main";
    }
}
