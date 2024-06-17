package Classes;

import Classes.interfaces.Displayable;

import java.util.List;

public class Rent implements Displayable {
    private static double rentSum;
    private int vehicleId;
    private String rentDate;
    private double rentCost;

    public Rent(){}

    public Rent(int vehicleId, String rentDate, double rentCost) {
        this.vehicleId = vehicleId;
        this.rentCost = rentCost;
        this.rentDate = rentDate;
    }

    public static void getRentSum(List<Rent> rents) {
        for (Rent rent : rents) {
            rentSum += rent.rentCost;
        }
    }
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getRentCost() {
        return rentCost;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return vehicleId + "," + rentDate + "," + rentCost;
    }
}
