package pl.klusek.michal.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity(name="tmotorcycle")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Motorcycle extends Vehicle{
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    public Motorcycle() {
    }

    public Motorcycle(int id, String brand, String model, int yearOfProduction, String licensePlate, double price, boolean rent, VehicleType vehicleType, Type type, Transmission transmission) {
        super(id, brand, model, yearOfProduction, licensePlate, price, rent, vehicleType);
        this.type = type;
        this.transmission = transmission;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getBrand() {
        return super.getBrand();
    }

    @Override
    public void setBrand(String brand) {
        super.setBrand(brand);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public int getYearOfProduction() {
        return super.getYearOfProduction();
    }

    @Override
    public void setYearOfProduction(int yearOfProduction) {
        super.setYearOfProduction(yearOfProduction);
    }

    @Override
    public String getLicensePlate() {
        return super.getLicensePlate();
    }

    @Override
    public void setLicensePlate(String licensePlate) {
        super.setLicensePlate(licensePlate);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public boolean isRent() {
        return super.isRent();
    }

    @Override
    public void setRent(boolean rent) {
        super.setRent(rent);
    }

    @Override
    public VehicleType getVehicleType() {
        return super.getVehicleType();
    }

    @Override
    public void setVehicleType(VehicleType vehicleType) {
        super.setVehicleType(vehicleType);
    }

    public enum Transmission{
        AUTOMATIC,
        MANUAL
    }

    public enum Type{
        SCOOTER,
        SPORT,
        STREET,
        CROSS,
        CRUISER,
        CHOPPER
    }
}
