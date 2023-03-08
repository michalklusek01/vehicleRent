package pl.klusek.michal.dao;

import pl.klusek.michal.model.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    List<Vehicle> getFilteredVehicles(String pattern);
    List<Vehicle> getAllVehicles();
    void addVehicle(Vehicle vehicle);
    Vehicle findVehicleByLicensePlate(String licensePlate);
    void updateVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);

    List<Vehicle> getFilteredVehiclesByType(String vehicleType);
    List<Vehicle> getFilteredVehiclesByTransmission(String transmission_type);
    void deleteVehicle(Vehicle vehicle);
}
