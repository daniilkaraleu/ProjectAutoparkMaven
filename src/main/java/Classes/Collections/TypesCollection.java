package Classes.Collections;

import Classes.VehicleType;
import Classes.Collections.ObjectCreator.interfaces.Creator;
import UtilFiles.Display;

public class TypesCollection extends AbstractAutoCollection<VehicleType> {
    public TypesCollection(){}
    public void display() {
        System.out.println("TYPES");
        Display.display(this.getList());
    }
}
