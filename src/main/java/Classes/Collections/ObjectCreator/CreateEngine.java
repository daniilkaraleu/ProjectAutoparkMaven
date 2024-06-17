package Classes.Collections.ObjectCreator;

import Classes.Engines.DieselEngine;
import Classes.Engines.ElectricalEngine;
import Classes.Engines.Engine;
import Classes.Engines.GasolineEngine;

public class CreateEngine {
    public Engine createEngine(String engineType, String[] data) {
        if (engineType.equals("Diesel")) {
            return new DieselEngine(Double.parseDouble(data[9]), Double.parseDouble(data[10]), Integer.parseInt(data[11]));
        }
        else if (engineType.equals("Gasoline")) {
            return new GasolineEngine(Double.parseDouble(data[9]), Double.parseDouble(data[10]), Integer.parseInt(data[11]));
        } else {
            return new ElectricalEngine(Integer.parseInt(data[9]), Double.parseDouble(data[10]));
        }
    }
}
