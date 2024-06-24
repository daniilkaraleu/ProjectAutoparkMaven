package Project.Classes;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.impl.ParserVehiclesFromDB;
import Project.Classes.interfaces.Fixer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Workroom {
    @Autowired
    private Fixer mechanic;

    public void checkAllVehicle(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> mechanic.detectAndRepair(vehicle));
    }

}
