package pl.klusek.michal.model;

import javax.persistence.*;
@Entity(name="tcar")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Car extends Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Fuel fuelType;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    boolean airConditioning;

    public Car() {
    }

    public Car(int id, String brand, String model, int yearOfProduction, String licensePlate, double price, boolean isRent, int id1, Fuel fuelType, Transmission transmission, boolean airConditioning) {
        super(id, brand, model, yearOfProduction, licensePlate, price, isRent);
        this.id = id1;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.airConditioning = airConditioning;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Fuel getFuelType() {
        return fuelType;
    }

    public void setFuelType(Fuel fuelType) {
        this.fuelType = fuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
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

    public enum Fuel{
        GASOLINE,
        DIESEL,
        GAS
    }

    public enum Transmission{
        MANUAL,
        AUTOMATIC
    }
}
