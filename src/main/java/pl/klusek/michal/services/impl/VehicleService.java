package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IVehicleDAO;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IVehicleService;
import pl.klusek.michal.session.SessionObject;

import java.util.List;
@Service
public class VehicleService implements IVehicleService {
    @Autowired
    IVehicleDAO vehicleDAO;
    @Autowired
    SessionObject sessionObject;

    @Override
    public List<Vehicle> getFilteredVehicles() {
        if (this.sessionObject.getFindPattern() != null && !this.sessionObject.getFindPattern().equals("")) {
            return this.vehicleDAO.getFilteredVehicles(this.sessionObject.getFindPattern());
        }else {
            return this.vehicleDAO.getAllVehicles();
        }
    }

    @Override
    public Vehicle getVehicleByData(String brand, String model, String licensePlate) {
        return this.vehicleDAO.getVehicleByData(brand, model, licensePlate);
    }
}
