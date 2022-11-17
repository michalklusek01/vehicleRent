package pl.klusek.michal.dao;

import pl.klusek.michal.model.Motorcycle;

import java.util.List;

public interface IMotorcycleDAO {
    List<Motorcycle> getAllMotorcycles();
    void addMotorcycle(Motorcycle motorcycle);
    Motorcycle FindMotorcycleByLicensePlate(String plate);
    void updateMotorcycle(Motorcycle motorcycle);
    Motorcycle getMotorcycleById(int motorcycleId);
}
