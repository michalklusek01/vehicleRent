package pl.klusek.michal.dao;

import pl.klusek.michal.model.Reservation;
import pl.klusek.michal.model.User;

import java.util.List;

public interface IReservationDAO {
    public void addReservation(Reservation reservation);
    public void deleteReservation(Reservation reservation);
    public List<Reservation> getReservationsByUserId(int id);
    public List<Reservation> getReservationsByVehicleId(int userId);
    public List<Reservation> getAllReservations();
    public Reservation getReservationById(int id);


}
