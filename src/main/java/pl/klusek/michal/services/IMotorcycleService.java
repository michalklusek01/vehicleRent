package pl.klusek.michal.services;

import pl.klusek.michal.model.Motorcycle;

import java.util.List;

public interface IMotorcycleService {
    boolean addMotorcycle(Motorcycle motorcycle);
    Motorcycle findMotorcycleByLicensePlate(String licensePlate);
    boolean updateMotorcycle(Motorcycle motorcycle);
    List<Motorcycle> getAllMotorcycles();
}
