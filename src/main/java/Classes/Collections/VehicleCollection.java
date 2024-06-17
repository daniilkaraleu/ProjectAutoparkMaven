package Classes.Collections;

import Classes.Vehicle;
import Classes.Collections.ObjectCreator.interfaces.Creator;
import UtilFiles.Display;

public class VehicleCollection extends AbstractAutoCollection<Vehicle> {
    public VehicleCollection(){}

    public void display() {
        System.out.println("VEHICLES");
        Display.display(this.getList());
    }
}
