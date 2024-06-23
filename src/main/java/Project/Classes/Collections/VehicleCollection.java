package Project.Classes.Collections;

import Project.Classes.Vehicle;
import Project.Classes.UtilFiles.Display;

public class VehicleCollection extends AbstractAutoCollection<Vehicle> {
    public VehicleCollection(){}

    public void display() {
        System.out.println("VEHICLES");
        Display.display(this.getList());
    }
}
