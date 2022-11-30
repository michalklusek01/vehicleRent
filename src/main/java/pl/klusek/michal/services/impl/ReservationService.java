package pl.klusek.michal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klusek.michal.dao.IReservationDAO;
import pl.klusek.michal.dao.IVehicleDAO;
import pl.klusek.michal.model.Reservation;
import pl.klusek.michal.model.User;
import pl.klusek.michal.model.Vehicle;
import pl.klusek.michal.services.IReservationService;
import pl.klusek.michal.session.SessionObject;

import java.time.Duration;
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

    @Autowired
    IVehicleDAO vehicleDAO;

    @Override
    public void addReservation(User user, Vehicle vehicle, LocalDate dateFrom, LocalDate dateTo) {
        if(!(this.reservationDAO.getReservationsByVehicleId(vehicle.getId()).isEmpty())) {
            Iterator<Reservation> iterator = this.reservationDAO.getReservationsByVehicleId(vehicle.getId()).iterator();

            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (dateFrom.equals(reservation.getDateFrom()) || dateFrom.equals(reservation.getDateTo()) || dateTo.equals(reservation.getDateFrom()) || dateTo.equals(reservation.getDateTo())) {
                    this.sessionObject.setInfo("Pojazd niedostepny!");
                    break;
                }
                if (dateFrom.isAfter(reservation.getDateFrom())) {
                    if (dateFrom.isBefore(reservation.getDateTo()) && dateTo.isAfter(reservation.getDateTo())) {
                        this.sessionObject.setInfo("Pojazd niedostepny!");
                        break;
                    } else if(dateFrom.isBefore(reservation.getDateTo()) && dateTo.isBefore(reservation.getDateTo())){
                        this.sessionObject.setInfo("Pojazd niedostepny!");
                        break;
                    }else if (dateTo.isAfter(reservation.getDateTo())) {
                        this.sessionObject.setInfo("Pojazd zarezerwowany!");
                        long duration = ChronoUnit.DAYS.between(dateFrom, dateTo);
                        double totalPrice = duration*reservation.getVehicle().getPrice();
                        Reservation addReservation1 = new Reservation(1, vehicle, user, dateFrom, dateTo, totalPrice);
                        this.reservationDAO.addReservation(addReservation1);
                        break;
                    }
                } else if (dateFrom.isBefore(reservation.getDateFrom())) {
                    if(dateTo.isAfter(reservation.getDateFrom())) {
                        this.sessionObject.setInfo("Pojazd niedostepny!");
                        break;
                    } else if(dateTo.isBefore(reservation.getDateFrom())){
                        this.sessionObject.setInfo("Pojazd zarezerwowany!");
                        long duration = ChronoUnit.DAYS.between(dateFrom, dateTo);
                        double totalPrice = duration*reservation.getVehicle().getPrice();
                        Reservation addReservation2 = new Reservation(1, vehicle, user, dateFrom, dateTo, totalPrice);
                        this.reservationDAO.addReservation(addReservation2);
                        break;
                    }
                }

                //-------wersja z equal, isAfter, isBefore scalona
/*                if(dateFrom.equals(reservation.getDateFrom()) || dateTo.equals(reservation.getDateFrom())
                        || dateFrom.equals(reservation.getDateTo()) || dateTo.equals(reservation.getDateTo())
                        || (dateFrom.isAfter(reservation.getDateFrom()) && dateTo.isBefore(reservation.getDateTo()))
                        || (dateFrom.isBefore(reservation.getDateFrom()) && dateTo.isAfter(reservation.getDateFrom()) && dateTo.isBefore(reservation.getDateTo()))
                        || (dateFrom.isAfter(reservation.getDateFrom()) && dateFrom.isBefore(reservation.getDateTo()) && dateTo.isAfter(reservation.getDateTo()))){
                    this.sessionObject.setInfo("Pojazd w tym terminie niedostępny!!");
                    System.out.println("Pojazd w tym terminie niedostępny!!");
                    break;
                } else {
                    System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
                    this.sessionObject.setInfo("Pojazd w tym terminie dostępny i zarezerwowany!!");
                    System.out.println("jestem w elsie");
                    Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo);
                    this.reservationDAO.addReservation(addReservation);
                    break;
                }*/

                //--------wersja z algorytmem z neta:
/*                if((dateFrom.compareTo(reservation.getDateFrom()) >= 0) || (dateTo.compareTo(reservation.getDateFrom()) >= 0)) {
                    if((dateFrom.compareTo(reservation.getDateTo()) <= 0) || (dateTo.compareTo(reservation.getDateTo()) <= 0)) {
                        this.sessionObject.setInfo("Pojazd w tym terminie niedostępny!");
                        System.out.println("Pojazd w tym terminie niedostępny!");
                        break;
                    } else {
                        System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
                        this.sessionObject.setInfo("Pojazd w tym terminie dostępny i zarezerwowany!!");
                        Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo);
                        this.reservationDAO.addReservation(addReservation);
                        break;
                    }
                } else {
                    System.out.println("Pojazd w tym terminie dostępny i zarezerwowany!!");
                    this.sessionObject.setInfo("Pojazd w tym terminie dostępny i zarezerwowany!!");
                    Reservation addReservation = new Reservation(1, vehicle, user, dateFrom, dateTo);
                    this.reservationDAO.addReservation(addReservation);
                    break;
                }*/

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
        return this.reservationDAO.getReservationsByUserId(id);
    }

/*    @Override
    public void checkIfVehicleIsRent() {
        LocalDate localDate = LocalDate.now();
        Iterator<Reservation> iterator = this.reservationDAO.getAllReservations().iterator();

        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if(localDate.equals(reservation.getDateFrom()) || localDate.equals(reservation.getDateTo())){
                Vehicle vehicle = reservation.getVehicle();
                vehicle.setRent(true);
                this.vehicleDAO.updateVehicle(vehicle);
            }else if(localDate.isAfter(reservation.getDateFrom()) && localDate.isBefore(reservation.getDateTo())){
                Vehicle vehicle = reservation.getVehicle();
                vehicle.setRent(true);
                this.vehicleDAO.updateVehicle(vehicle);
            }else{
                Vehicle vehicle = reservation.getVehicle();
                vehicle.setRent(false);
                this.vehicleDAO.updateVehicle(vehicle);
            }
        }
    }*/
}
