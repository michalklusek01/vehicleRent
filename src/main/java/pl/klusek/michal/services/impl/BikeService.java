package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IBikeDAO;
import pl.klusek.michal.model.Bike;
import pl.klusek.michal.model.Car;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IBikeService;
import pl.klusek.michal.session.SessionObject;
import pl.klusek.michal.validators.VehicleValidator;

import java.util.List;

@Service
public class BikeService implements IBikeService {
    @Autowired
    IBikeDAO bikeDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public boolean addBike(Bike bike) {
        if(!VehicleValidator.validate(bike)){
            this.sessionObject.setInfo("Brak uzupełnionych danych!");
            return false;
        }
        Bike bikeFromDatabase = this.bikeDAO.FindBikeByLicensePlate(bike.getLicensePlate());
        if(bikeFromDatabase != null){
            this.sessionObject.setInfo("Pojazd o podanym numerze rejestracyjnym już jest w bazie!");
            return false;
        } else {
            bike.setVehicleType(Vehicle.VehicleType.BIKE);
            this.bikeDAO.addBike(bike);
        }
        return true;
    }

    @Override
    public Bike findBikeByLicensePlate(String licensePlate) {
        return this.bikeDAO.FindBikeByLicensePlate(licensePlate);
    }

    @Override
    public boolean updateBike(Bike bike) {
        this.bikeDAO.updateBike(bike);
        return true;
    }

    @Override
    public List<Bike> getAllBikes() {
        return this.bikeDAO.getAllBikes();
    }
}
