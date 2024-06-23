package Project.Classes.Collections.ObjectCreator;

import Project.Classes.Collections.ObjectCreator.interfaces.Creator;
import Project.Classes.Infrastructure.dto.entity.Types;
import Project.Classes.UtilFiles.LineProcessor;

public class CreateTypes implements Creator<Types> {
    public Types createObject(String line){
        String []data = LineProcessor.splitLine(line);

        return Types.builder()
                .name(data[1])
                .coefTaxes(Double.parseDouble(data[2]))
                .build();
    }
}
