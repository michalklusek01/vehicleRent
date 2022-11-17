package pl.klusek.michal.dao;

import pl.klusek.michal.model.Car;

import java.util.List;

public interface ICarDAO {
    List<Car> getAllCars();
    void addCar(Car car);
    Car FindCarByLicensePlate(String plate);
    void updateCar(Car car);
    Car getCarById(int carId);
}
