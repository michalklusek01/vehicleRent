package pl.klusek.michal.model;

import javax.persistence.*;
@Entity(name="tbike")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Bike extends Vehicle{
    @Enumerated(EnumType.STRING)
    private Type type;

    public Bike() {
    }

    public Bike(int id, String brand, String model, int yearOfProduction, String licensePlate, double price, boolean rent, VehicleType vehicleType, Type type) {
        super(id, brand, model, yearOfProduction, licensePlate, price, rent, vehicleType);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public enum Type{
        ELECTRIC,
        ROAD,
        MOUNTAIN,
        UTILITY
    }
}
