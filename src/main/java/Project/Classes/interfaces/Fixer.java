package Project.Classes.interfaces;

import Project.Classes.Vehicle;

import java.util.Map;

public interface Fixer {
    public void repair(Vehicle vehicle);
    default void detectAndRepair(Vehicle vehicle) {
        if (isBroken(vehicle)) {
            repair(vehicle);
        }
    }
    boolean isBroken(Vehicle vehicle);
}
