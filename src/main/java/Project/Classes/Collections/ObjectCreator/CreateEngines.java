package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Collections.ObjectCreator.interfaces.Creator;
import Project.Classes.Infrastructure.dto.entity.Engines;
import Project.Classes.UtilFiles.LineProcessor;

public class CreateEngines implements Creator<Engines> {
    @Override
    public Engines createObject(String line) {
        String []data = LineProcessor.splitLine(line);

        Double engineCapacity = data[8].equals("Electrical") ? null : Double.parseDouble(data[9]);
        Double fuelConsumption = data[8].equals("Electrical") ? Double.parseDouble(data[9]) : Double.parseDouble(data[10]);
        Double tankCapacity = data[8].equals("Electrical") ? Double.parseDouble(data[10]) : Double.parseDouble(data[11]);


        return Engines.builder()
                .name(data[8])
                .engineCapacity(engineCapacity)
                .fuelConsumption(fuelConsumption)
                .tankCapacity(tankCapacity)
                .build();
    }
}
