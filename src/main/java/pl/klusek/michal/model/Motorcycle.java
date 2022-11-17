package pl.klusek.michal.model;

import javax.persistence.*;
@Entity(name="tmotorcycle")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Motorcycle extends Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    public Motorcycle() {
    }

    public Motorcycle(int id, String brand, String model, int yearOfProduction, String licensePlate, double price, boolean isRent, int id1, Type type, Transmission transmission) {
        super(id, brand, model, yearOfProduction, licensePlate, price, isRent);
        this.id = id1;
        this.type = type;
        this.transmission = transmission;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
