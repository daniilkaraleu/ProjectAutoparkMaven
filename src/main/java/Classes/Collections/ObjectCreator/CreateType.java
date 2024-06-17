package Classes.Collections.ObjectCreator;

import Classes.VehicleType;
import Classes.Collections.ObjectCreator.interfaces.Creator;
import UtilFiles.LineProcessor;

public class CreateType implements Creator<VehicleType> {
    @Override
    public VehicleType createObject(String line){
        String []data = LineProcessor.splitLine(line);

        return new VehicleType(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
    }

}
