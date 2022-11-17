package pl.klusek.michal.dao;

import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Car;

import java.util.List;

public interface IBikeDAO {
    List<Bike> getAllBikes();
    void addBike(Bike bike);
    void updateBike(Bike bike);
    Bike getBikeById(int bikeId);
}
