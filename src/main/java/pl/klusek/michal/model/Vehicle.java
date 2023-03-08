package pl.klusek.michal.model;

import javax.persistence.*;

@Entity(name = "tvehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int id;
    private String brand;
    private String model;
    private int yearOfProduction;
    @Column(unique = true)
    private String licensePlate;
    private double price;
    private boolean rent;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(int id, String brand, String model, int yearOfProduction, String licensePlate, double price, boolean rent, VehicleType vehicleType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.licensePlate = licensePlate;
        this.price = price;
        this.rent = rent;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", licensePlate='" + licensePlate + '\'' +
                ", price=" + price +
                ", rent=" + rent +
                '}';
    }

        public enum VehicleType{
            CAR,
            MOTORCYCLE,
            BIKE
    }
}
