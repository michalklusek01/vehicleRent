package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IVehicleDAO;
import pl.klusek.michal.model.Car;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.services.IVehicleService;
import pl.klusek.michal.session.SessionObject;
import pl.klusek.michal.validators.VehicleValidator;

import java.util.List;
@Service
public class VehicleService implements IVehicleService {
    @Autowired
    IVehicleDAO vehicleDAO;
    @Autowired
    SessionObject sessionObject;

    @Autowired
    IReservationService reservationService;

    @Override
    public List<Vehicle> getFilteredVehicles() {
        if(this.sessionObject.getFindPattern() != null && !this.sessionObject.getFindPattern().equals("")) {
            return this.vehicleDAO.getFilteredVehicles(this.sessionObject.getFindPattern());
        }else if(this.sessionObject.getVehicleTypeFilter() != null){
            return this.vehicleDAO.getFilteredVehiclesByType(this.sessionObject.getVehicleTypeFilter());
        }else if(this.sessionObject.getTransmissionTypeFilter() != null){
            return this.vehicleDAO.getFilteredVehiclesByTransmission(this.sessionObject.getTransmissionTypeFilter());
        }else{
            return this.vehicleDAO.getAllVehicles();
        }
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        if(!VehicleValidator.validate(vehicle)){
            this.sessionObject.setInfo("Brak uzupełnionych danych!");
            return false;
        }
        Vehicle vehicleFromDb = this.vehicleDAO.findVehicleByLicensePlate(vehicle.getLicensePlate());
        if(vehicleFromDb != null){
            this.sessionObject.setInfo("Pojazd o podanym numerze rejestracyjnym już jest w bazie!");
            return false;
        } else {
            this.vehicleDAO.addVehicle(vehicle);
        }
        return true;
    }

    @Override
    public boolean updateVehicle(String licensePlate, Vehicle vehicle) {
        Vehicle vehicle1 = this.vehicleDAO.findVehicleByLicensePlate(licensePlate);
        if(vehicle1 == null){
            return false;
        }

        vehicle1.setBrand(vehicle.getBrand());
        vehicle1.setModel(vehicle.getModel());
        vehicle1.setPrice(vehicle.getPrice());
        vehicle1.setLicensePlate(vehicle.getLicensePlate());
        vehicle1.setYearOfProduction(vehicle.getYearOfProduction());
        vehicle1.setRent(vehicle.isRent());
        if(vehicle1 instanceof Car){
            Car car1 = (Car) vehicle1;
            ((Car) vehicle1).setAirConditioning(car1.isAirConditioning());

        }
        this.vehicleDAO.updateVehicle(vehicle1);
        return true;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return this.vehicleDAO.getVehicleById(id);
    }

    @Override
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return this.vehicleDAO.findVehicleByLicensePlate(licensePlate);
    }
}
