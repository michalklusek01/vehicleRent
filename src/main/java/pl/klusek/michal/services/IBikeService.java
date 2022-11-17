package pl.klusek.michal.services;


import pl.klusek.michal.model.Bike;

import java.util.List;

public interface IBikeService {
    boolean addBike(Bike bike);
    boolean updateBike(Bike bike);
    List<Bike> getAllBikes();
}
