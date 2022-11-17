package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.ICarDAO;
import pl.klusek.michal.model.Car;
import pl.klusek.michal.services.ICarService;
import pl.klusek.michal.session.SessionObject;
import pl.klusek.michal.validators.VehicleValidator;

import java.util.List;

@Service
public class CarService implements ICarService {
    @Autowired
    ICarDAO carDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public boolean addCar(Car car) {
        if(!VehicleValidator.validate(car)){
            this.sessionObject.setInfo("Brak uzupełnionych danych!");
            return false;
        }
        Car carFromDatabase = this.carDAO.FindCarByLicensePlate(car.getLicensePlate());
        if(carFromDatabase != null){
            this.sessionObject.setInfo("Pojazd o podanym numerze rejestracyjnym już jest w bazie!");
            return false;
        } else {
            this.carDAO.addCar(car);
        }
        return true;
    }

    @Override
    public Car findCarByLicensePlate(String licensePlate) {
        return this.carDAO.FindCarByLicensePlate(licensePlate);
    }

    @Override
    public boolean updateCar(Car car) {
        Car carFromDb = this.carDAO.FindCarByLicensePlate(car.getLicensePlate());
        if(carFromDb == null){
            return false;
        }
        carFromDb.setBrand(car.getBrand());
        carFromDb.setModel(car.getModel());
        carFromDb.setPrice(car.getPrice());
        carFromDb.setAirConditioning(car.isAirConditioning());
        carFromDb.setFuelType(car.getFuelType());
        carFromDb.setRent(false);
        carFromDb.setTransmission(car.getTransmission());

        this.carDAO.updateCar(carFromDb);
        return true;
    }

    @Override
    public List<Car> getAllCars() {
        return this.carDAO.getAllCars();
    }
}
