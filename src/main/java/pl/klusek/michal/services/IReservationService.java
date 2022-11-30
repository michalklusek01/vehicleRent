package pl.klusek.michal.services;

import pl.klusek.michal.model.Reservation;
import pl.klusek.michal.model.User;
import pl.klusek.michal.model.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    public void addReservation(User user, Vehicle vehicle, LocalDate dateFrom, LocalDate dateTo);

    public List<Reservation> getReservationsByUserId(int id);

/*    public void checkIfVehicleIsRent();*/
}
