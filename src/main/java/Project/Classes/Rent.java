package Project.Classes;

import Project.Classes.interfaces.Displayable;
import lombok.Builder;
import lombok.Setter;

import java.util.List;
@Setter
@Builder
public class Rent implements Displayable {
    private static double rentSum;
    private Long vehicleId;
    private String rentDate;
    private double rentCost;

    public Rent(){}

    public Rent(Long vehicleId, String rentDate, double rentCost) {
        this.vehicleId = vehicleId;
        this.rentCost = rentCost;
        this.rentDate = rentDate;
    }

    public static void getRentSum(List<Rent> rents) {
        for (Rent rent : rents) {
            rentSum += rent.rentCost;
        }
    }
    public Long getVehicleId() {
        return vehicleId;
    }

    public double getRentCost() {
        return rentCost;
    }

    public String getRentDate() {
        return rentDate;
    }

    @Override
    public String toString() {
        return vehicleId + "," + rentDate + "," + rentCost;
    }
}
