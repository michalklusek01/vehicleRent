package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IMotorcycleDAO;
import pl.klusek.michal.model.Motorcycle;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IMotorcycleService;
import pl.klusek.michal.session.SessionObject;
import pl.klusek.michal.validators.VehicleValidator;

import java.util.List;

@Service
public class MotorcycleService implements IMotorcycleService {
    @Autowired
    IMotorcycleDAO motorcycleDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public boolean addMotorcycle(Motorcycle motorcycle) {
        if(!VehicleValidator.validate(motorcycle)){
            this.sessionObject.setInfo("Brak uzupełnionych danych!");
            return false;
        }
        Motorcycle motorcycleFromDb = this.motorcycleDAO.FindMotorcycleByLicensePlate(motorcycle.getLicensePlate());
        if(motorcycleFromDb != null){
            this.sessionObject.setInfo("Pojazd o podanym numerze rejestracyjnym już jest w bazie!");
            return false;
        }
        motorcycle.setVehicleType(Vehicle.VehicleType.MOTORCYCLE);
        this.motorcycleDAO.addMotorcycle(motorcycle);
        return true;
    }

    @Override
    public Motorcycle findMotorcycleByLicensePlate(String licensePlate) {
        return this.motorcycleDAO.FindMotorcycleByLicensePlate(licensePlate);
    }

    @Override
    public boolean updateMotorcycle(Motorcycle motorcycle) {
        Motorcycle motorcycleFromDb = this.motorcycleDAO.FindMotorcycleByLicensePlate(motorcycle.getLicensePlate());
        if(motorcycleFromDb == null){
            return false;
        }

        motorcycleFromDb.setBrand(motorcycle.getBrand());
        motorcycleFromDb.setModel(motorcycle.getModel());
        motorcycleFromDb.setRent(false);
        motorcycleFromDb.setPrice(motorcycle.getPrice());
        motorcycleFromDb.setLicensePlate(motorcycle.getLicensePlate());
        motorcycleFromDb.setTransmission(motorcycle.getTransmission());
        motorcycleFromDb.setType(motorcycle.getType());
        motorcycleFromDb.setYearOfProduction(motorcycle.getYearOfProduction());

        this.motorcycleDAO.updateMotorcycle(motorcycleFromDb);
        return true;
    }

    @Override
    public List<Motorcycle> getAllMotorcycles() {
        return this.motorcycleDAO.getAllMotorcycles();
    }
}
