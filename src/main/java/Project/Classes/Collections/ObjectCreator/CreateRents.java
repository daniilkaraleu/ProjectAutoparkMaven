package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Collections.ObjectCreator.interfaces.Creator;
import Project.Classes.Infrastructure.dto.entity.Rents;
import Project.Classes.UtilFiles.LineProcessor;

public class CreateRents implements Creator<Rents> {
    public Rents createObject(String line){
        String []data = LineProcessor.splitLine(line);

        return Rents.builder()
                .vehicleId(Long.parseLong(data[0]))
                .rentDate(data[1])
                .rentCost(Double.parseDouble(data[2]))
                .build();
    }
}
