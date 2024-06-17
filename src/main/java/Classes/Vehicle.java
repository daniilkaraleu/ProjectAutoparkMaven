package Classes;

import Classes.Engines.Engine;
import Classes.interfaces.Displayable;
import Exceptions.NotVehicleException;
import Classes.Engines.interfaces.Startable;
import UtilFiles.TechnicalSpecialist;

import java.util.List;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> , Displayable {
    private static final double MASS_COEF = 0.0013;
    private static final double VEHICLE_TYPE_COEF = 30;
    private static final double FREE_TERM_OF_TAX = 5;

    private VehicleType vehicleType;
    private String model;
    private String registrationNumber;
    private double mass;
    private int yearOfManufacture;
    private double mileage;
    private VehicleColors color;
    private double tankVolume;
    private Startable engine;
    private int vehicleId;
    private List<Rent> rentHistory;


    public Vehicle(int vehicleId,
                   VehicleType vehicleType,
                   String model,
                   String registrationNumber,
                   double mass,
                   int yearOfManufacture,
                   double mileage,
                   VehicleColors color,
                   Engine engine,
                   double tankVolume,
                   List<Rent> vehicleRents) {

                this.vehicleId = vehicleId;
                this.vehicleType = vehicleType;
                this.engine = engine;
                this.model = model;
                this.registrationNumber = registrationNumber;
                this.mass = mass;
                this.yearOfManufacture = yearOfManufacture;
                this.mileage = mileage;
                this.color = color;
                this.tankVolume = tankVolume;
                this.rentHistory = vehicleRents;
    }

    public double getMass() {
        return mass;
    }

    public double getMileage() {
        return mileage;
    }

    public VehicleColors getColor() {
        return color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getTankVolume() {
        return tankVolume;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getModel() {
        return model;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<Rent> getRentHistory() {
        return rentHistory;
    }

    public void setRentHistory(List<Rent> rentHistory) {
        this.rentHistory = rentHistory;
    }

    public void setColor(VehicleColors color) {
        try {
            this.color = color;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " color wasn't set");
        }
    }

    public void setMass(double mass) {
        try {
            this.mass = mass;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " mass wasn't set");
        }
    }

    public void setMileage(double mileage) {
        try {
            this.mileage = mileage;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " mileage wasn't set");
        }
    }

    public void setModel(String model) {
        try {
            this.model = model;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " model name wasn't set");
        }
    }

    public void setVehicleType(VehicleType vehicleType) {
        try {
            this.vehicleType = vehicleType;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " vehicle type wasn't set");
        }
    }

    public void setRegistrationNumber(String registrationNumber) {
        try {
            this.registrationNumber = registrationNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " registration number wasn't set");
        }
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        try {
            this.yearOfManufacture = yearOfManufacture;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " year of manufacture wasn't set");
        }
    }

    public double getCalcTaxPerMonth() {
        return (mass * MASS_COEF) + (vehicleType.getTaxCoefficient() * engine.getTaxPerMonth() * VEHICLE_TYPE_COEF) + FREE_TERM_OF_TAX;
    }

    public Startable getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        try {
            this.engine = engine;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " engine wasn't set");
        }
    }

    public double getTotalIncome() {
        double totalIncome = 0;

        for (Rent rent : this.rentHistory) {
            totalIncome += rent.getRentCost();
        }

        return totalIncome;
    }

    public double getTotalProfit() {
        return this.getTotalIncome() - this.getCalcTaxPerMonth();
    }

    @Override
    public String toString() {
        return vehicleId + "         " + vehicleType.getType() + "      " + model + "    " + registrationNumber + " " +
                mass + " " + yearOfManufacture + " " + mileage + " " + color.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return this.vehicleType.equals(vehicle.vehicleType) && this.model.equals(vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, model);
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        if (this.getYearOfManufacture() - vehicle.getYearOfManufacture() != 0) {
            return this.getYearOfManufacture() - vehicle.getYearOfManufacture();
        } else {
            return (int) this.mileage - (int) vehicle.mileage;
        }
    }
}
