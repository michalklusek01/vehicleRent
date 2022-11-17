package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IReservationDAO;
import pl.klusek.michal.model.Reservation;
import pl.klusek.michal.model.User;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IReservationService;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    IReservationDAO reservationDAO;

    @Override
    public void addReservation(User user, Vehicle vehicle, LocalDate dateFrom, LocalDate dateTo) {
        Iterator<Reservation> iterator = this.reservationDAO.getReservationsByVehicleId(user.getId()).iterator();
        while(iterator.hasNext()){
            Reservation reservation = iterator.next();
            System.out.println(reservation);
            if(dateFrom.compareTo(reservation.getDateFrom()) >= 0){
                if(dateFrom.compareTo(reservation.getDateTo()) <= 0){
                    System.out.println("Pojazd w tym terminie zarezerwowany!");
                } else {
                    System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
                    Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo);
                    this.reservationDAO.addReservation(reservation);
                }
            } else {
                System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
                Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo);
                this.reservationDAO.addReservation(reservation);
            }
        }
    }

    @Override
    public List<Reservation> getReservationsByUserId(int id) {
        return this.reservationDAO.getReservationsByUserId(id);
    }
}
