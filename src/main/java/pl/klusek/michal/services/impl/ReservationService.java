package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IReservationDAO;
import pl.klusek.michal.model.Reservation;
import pl.klusek.michal.model.User;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.session.SessionObject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    IReservationDAO reservationDAO;
    @Autowired
    SessionObject sessionObject;


    @Override
    public void addReservation(User user, Vehicle vehicle,
                               LocalDate dateFrom, LocalDate dateTo) {
        if(!(this.reservationDAO.getReservationsByVehicleId(vehicle.getId()).isEmpty())) {
            Iterator<Reservation> iterator
                    = this.reservationDAO.getReservationsByVehicleId(vehicle.getId()).iterator();

            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (dateFrom.equals(reservation.getDateFrom())
                        || dateFrom.equals(reservation.getDateTo())
                        || dateTo.equals(reservation.getDateFrom())
                        || dateTo.equals(reservation.getDateTo())) {
                    this.sessionObject.setInfo("Pojazd w tym terminie jest niedostępny");
                    break;
                }
                if (dateFrom.isAfter(reservation.getDateFrom())) {
                    if (dateFrom.isBefore(reservation.getDateTo())
                            && dateTo.isAfter(reservation.getDateTo())) {
                        this.sessionObject.setInfo("Pojazd w tym terminie jest niedostępny");
                        break;
                    } else if(dateFrom.isBefore(reservation.getDateTo())
                            && dateTo.isBefore(reservation.getDateTo())){
                        this.sessionObject.setInfo("Pojazd w tym terminie jest niedostępny!");
                        break;
                    }else if (dateTo.isAfter(reservation.getDateTo())) {
                        this.sessionObject.setInfo("Pojazd zarezerwowany!");
                        long duration = ChronoUnit.DAYS.between(dateFrom, dateTo);
                        double totalPrice = duration*reservation.getVehicle().getPrice();
                        Reservation addReservation1 =
                                new Reservation(1, vehicle, user, dateFrom, dateTo, totalPrice);
                        this.reservationDAO.addReservation(addReservation1);
                        break;
                    }
                } else if (dateFrom.isBefore(reservation.getDateFrom())) {
                    if(dateTo.isAfter(reservation.getDateFrom())) {
                        this.sessionObject.setInfo("Pojazd w tym terminie jest niedostępny!");
                        break;
                    } else if(dateTo.isBefore(reservation.getDateFrom())){
                        this.sessionObject.setInfo("Pojazd zarezerwowany!");
                        long duration = ChronoUnit.DAYS.between(dateFrom, dateTo);
                        double totalPrice = duration*reservation.getVehicle().getPrice();
                        Reservation addReservation2 =
                                new Reservation(1, vehicle, user, dateFrom, dateTo, totalPrice);
                        this.reservationDAO.addReservation(addReservation2);
                        break;
                    }
                }
            }
        }

        if(this.reservationDAO.getReservationsByVehicleId(vehicle.getId()).isEmpty()){
            System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
            this.sessionObject.setInfo("Pojazd w tym terminie dostępny i zarezerwowany!!");
            long duration = ChronoUnit.DAYS.between(dateFrom, dateTo);
            double totalPrice = duration*vehicle.getPrice();
            Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo, totalPrice);
            this.reservationDAO.addReservation(addReservation);
        }
    }

    @Override
    public List<Reservation> getReservationsByUserId(int id) {
        if(this.reservationDAO.getReservationsByUserId(id) == null){
            return null;
        }
        return this.reservationDAO.getReservationsByUserId(id);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        this.reservationDAO.deleteReservation(reservation);
    }

    @Override
    public Reservation getReservationById(int id) {
        if(this.reservationDAO.getReservationById(id) == null){
            return null;
        }
        Reservation reservationFromDb = this.reservationDAO.getReservationById(id);
        return reservationFromDb;
    }
}
