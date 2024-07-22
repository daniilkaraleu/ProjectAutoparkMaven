package Project.Classes;

import Project.Classes.Engines.Engine;
import Project.Classes.interfaces.Displayable;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
@Setter
public class Vehicle implements Comparable<Vehicle> , Displayable, Cloneable {
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
    private Engine engine;
    private Long vehicleId;
    private List<Rent> rentHistory;

    private Boolean wasWorking;
    private Boolean fixed;

    {
        wasWorking = false;
        fixed = true;
    }

    public Vehicle(Long vehicleId,
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


    public double getCalcTaxPerMonth() {
        return (mass * MASS_COEF) + (vehicleType.getTaxCoefficient() * engine.getTaxPerMonth() * VEHICLE_TYPE_COEF) + FREE_TERM_OF_TAX;
    }


    public void setEngine(Engine engine) {
        try {
            this.engine = engine;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "\n" + this.model + " engine wasn't set");
        }
    }



    public double getTotalProfit() {
        return this.getRentsIncome() - this.getCalcTaxPerMonth();
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
    public Vehicle clone() throws CloneNotSupportedException {
        Vehicle clonedVehicle = (Vehicle) super.clone();
        clonedVehicle.engine = this.getEngine().clone();

        return clonedVehicle;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        if (this.getYearOfManufacture() - vehicle.getYearOfManufacture() != 0) {
            return this.getYearOfManufacture() - vehicle.getYearOfManufacture();
        } else {
            return (int) this.mileage - (int) vehicle.mileage;
        }
    }
    public double getRentsIncome(){
        return rentHistory.stream().mapToDouble(Rent::getRentCost).sum();
    }

}
