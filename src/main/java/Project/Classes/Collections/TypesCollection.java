package Project.Classes.Collections;

import Project.Classes.VehicleType;
import Project.Classes.UtilFiles.Display;

public class TypesCollection extends AbstractAutoCollection<VehicleType> {
    public TypesCollection(){}
    public void display() {
        System.out.println("TYPES");
        Display.display(this.getList());
    }
}
