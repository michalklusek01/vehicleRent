package pl.klusek.michal.dao;

import pl.klusek.michal.model.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    List<Vehicle> getFilteredVehicles(String pattern);
    List<Vehicle> getAllVehicles();

    Vehicle getVehicleByData(String brand, String model, String licensePlate);
}
