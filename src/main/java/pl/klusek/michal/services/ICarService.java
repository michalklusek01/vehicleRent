package pl.klusek.michal.services;

import pl.klusek.michal.model.Car;

import java.util.List;

public interface ICarService {
    boolean addCar(Car car);
    Car findCarByLicensePlate(String licensePlate);
    boolean updateCar(Car car);
    List<Car> getAllCars();
}
