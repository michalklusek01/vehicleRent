package pl.klusek.michal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "treservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    User user;
    LocalDate dateFrom;
    LocalDate dateTo;

    public Reservation() {
    }

    public Reservation(int id, Vehicle vehicle, User user, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.vehicle = vehicle;
        this.user = user;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
