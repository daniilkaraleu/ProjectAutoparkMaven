package Classes;

import Infrastructure.core.annotations.Autowired;
import Classes.interfaces.Fixer;

import java.util.List;

public class Workroom {
    @Autowired
    private Fixer mechanic;

    public void checkAllVehicle(List<Vehicle> vehicles) {

    }

    public Fixer getMechanic() {
        return mechanic;
    }

    public void setMechanic(Fixer mechanic) {
        this.mechanic = mechanic;
    }
}
