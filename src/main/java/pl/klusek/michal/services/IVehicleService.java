package pl.klusek.michal.services;

import pl.klusek.michal.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    public List<Vehicle> getFilteredVehicles();
//    Vehicle getVehicleByData(String brand, String model, String licensePlate);
    Vehicle getVehicleByLicensePlate(String licensePlate);
    boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(String licensePlate, Vehicle vehicle);
    Vehicle getVehicleById(int id);
}
