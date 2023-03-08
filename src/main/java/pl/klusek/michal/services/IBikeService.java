package pl.klusek.michal.services;


import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Car;

import java.util.List;

public interface IBikeService {
    boolean addBike(Bike bike);
    Bike findBikeByLicensePlate(String licensePlate);
    boolean updateBike(Bike bike);
    List<Bike> getAllBikes();
}
