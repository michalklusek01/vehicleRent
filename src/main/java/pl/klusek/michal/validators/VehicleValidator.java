package pl.klusek.michal.validators;

import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Vehicle;

public class VehicleValidator {
    public static boolean validate(Vehicle vehicle) {
        if(vehicle instanceof Bike) {
            if (vehicle.getBrand().equals("") || vehicle.getModel().equals("") || vehicle.getYearOfProduction() == 0) {
                return false;
            }
        }

        if(!(vehicle instanceof Bike)) {
            if (vehicle.getBrand().equals("") || vehicle.getModel().equals("") || vehicle.getLicensePlate().equals("") || vehicle.getYearOfProduction() == 0) {
                return false;
            }
        }

        return true;
    }
}
