package pl.klusek.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.klusek.michal.dao.IBikeDAO;
import pl.klusek.michal.dao.ICarDAO;
import pl.klusek.michal.dao.IMotorcycleDAO;
import pl.klusek.michal.dao.IUserDAO;
import pl.klusek.michal.model.*;

@Controller
public class HackController {
    @Autowired
    ICarDAO carDAO;
    @Autowired
    IBikeDAO bikeDAO;
    @Autowired
    IMotorcycleDAO motorcycleDAO;

    @Autowired
    IUserDAO userDAO;

    @RequestMapping(value = "/hack/addVehicles", method = RequestMethod.GET)
    public String addVehicles(){
        carDAO.addCar(new Car(1, "BMW", "X3", 2018, "KR09SJ2", 320, false, Vehicle.VehicleType.CAR, Car.Fuel.DIESEL, Car.Transmission.MANUAL, true));
        carDAO.addCar(new Car(1, "BMW", "X5", 2019, "KR08JK5", 378, false,Vehicle.VehicleType.CAR, Car.Fuel.GASOLINE, Car.Transmission.AUTOMATIC, true));
        carDAO.addCar(new Car(1, "BMW", "5", 2020, "KRKJS98", 350, false, Vehicle.VehicleType.CAR, Car.Fuel.DIESEL, Car.Transmission.AUTOMATIC, true));
        carDAO.addCar(new Car(1, "Audi", "A6", 2021, "KR2TS14", 365, false, Vehicle.VehicleType.CAR, Car.Fuel.GASOLINE, Car.Transmission.MANUAL, true));
        carDAO.addCar(new Car(1, "Audi", "Q7", 2019, "KR7K762", 390, false, Vehicle.VehicleType.CAR, Car.Fuel.DIESEL, Car.Transmission.AUTOMATIC, true));
        carDAO.addCar(new Car(1, "Audi", "A4", 2022, "KR986J2", 410, false, Vehicle.VehicleType.CAR, Car.Fuel.GASOLINE, Car.Transmission.AUTOMATIC, true));
        carDAO.addCar(new Car(1, "Opel", "Astra", 2022, "KR8P98R", 290, false, Vehicle.VehicleType.CAR, Car.Fuel.GASOLINE, Car.Transmission.MANUAL, true));
        carDAO.addCar(new Car(1, "Toyota", "Corolla", 2021, "KR8S77K", 290, false, Vehicle.VehicleType.CAR,Car.Fuel.DIESEL, Car.Transmission.AUTOMATIC, true));
        carDAO.addCar(new Car(1, "Toyota", "RAV4", 2021, "KR34UN8", 300, false, Vehicle.VehicleType.CAR, Car.Fuel.GASOLINE, Car.Transmission.MANUAL, true));
        carDAO.addCar(new Car(1, "Citroen", "C4", 2018, "KR238JA", 250, false, Vehicle.VehicleType.CAR, Car.Fuel.GAS, Car.Transmission.MANUAL, false));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "BMW", "R", 2022, "KR763IW", 220, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.CHOPPER, Motorcycle.Transmission.MANUAL));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "Yamaha", "FJR", 2015, "KR489Z7", 150, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.SCOOTER, Motorcycle.Transmission.MANUAL));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "Harley-Davidson", "Street 750", 2018, "KR3294F", 180, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.STREET, Motorcycle.Transmission.MANUAL));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "Yamaha", "R125", 2021, "KR84GN4", 190, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.SPORT, Motorcycle.Transmission.MANUAL));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "Yamaha", "Aerox", 2012, "KR9JK4Y", 140, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.SCOOTER, Motorcycle.Transmission.AUTOMATIC));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "Yamaha", "Tenere", 2021, "KR8SA97", 180, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.CRUISER, Motorcycle.Transmission.MANUAL));
        motorcycleDAO.addMotorcycle(new Motorcycle(1, "KTM", "Adventure", 2020, "KR89C7D", 200, false, Vehicle.VehicleType.MOTORCYCLE, Motorcycle.Type.CROSS, Motorcycle.Transmission.MANUAL));
        bikeDAO.addBike(new Bike(1, "Kross", "HEXAGON", 2021, "Bike1", 50, false, Vehicle.VehicleType.BIKE, Bike.Type.MOUNTAIN));
        bikeDAO.addBike(new Bike(1, "Kross", "LEVEL 2.0", 2020, "Bike2", 50, false, Vehicle.VehicleType.BIKE, Bike.Type.MOUNTAIN));
        bikeDAO.addBike(new Bike(1, "Giant", "PROPEL ADVANCED PRO 2", 2020, "Bike3", 90, false, Vehicle.VehicleType.BIKE, Bike.Type.ROAD));
        bikeDAO.addBike(new Bike(1, "Giant", "TCR ADVANCED", 2021, "Bike4", 90, false, Vehicle.VehicleType.BIKE, Bike.Type.ROAD));
        bikeDAO.addBike(new Bike(1, "Specialized", "TURBO TERO 4.0 EQ", 2022, "Bike5", 150, false, Vehicle.VehicleType.BIKE, Bike.Type.ELECTRIC));
        bikeDAO.addBike(new Bike(1, "Kross", "EVADO 5.0", 2021, "Bike6", 50, false, Vehicle.VehicleType.BIKE, Bike.Type.UTILITY));
        return "redirect:/main";
    }
}
