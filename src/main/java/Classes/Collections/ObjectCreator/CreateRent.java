package Classes.Collections.ObjectCreator;

import Classes.Rent;
import Classes.Collections.ObjectCreator.interfaces.Creator;
import UtilFiles.LineProcessor;

public class CreateRent implements Creator<Rent> {
    @Override
    public Rent createObject(String line) {
        String []data = LineProcessor.splitLine(line);

        return new Rent(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
    }


}
