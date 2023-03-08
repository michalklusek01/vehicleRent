package pl.klusek.michal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "treservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    User user;
    LocalDate dateFrom;
    LocalDate dateTo;
    double price;

    public Reservation() {
    }


    public Reservation(int id, Vehicle vehicle, User user, LocalDate dateFrom, LocalDate dateTo, double price) {
        this.id = id;
        this.vehicle = vehicle;
        this.user = user;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
