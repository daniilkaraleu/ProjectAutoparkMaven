package Project.Classes.interfaces;

import Project.Classes.Vehicle;

import java.util.Map;

public interface Fixer {
    public Map<String, Integer> detectBreaking(Vehicle vehicle);
    public void repair(Vehicle vehicle);
    default void detectAndRepair(Vehicle vehicle) {
        if (isBroken(vehicle)) {
            repair(vehicle);
        }
    }
    boolean isBroken(Vehicle vehicle);
}
