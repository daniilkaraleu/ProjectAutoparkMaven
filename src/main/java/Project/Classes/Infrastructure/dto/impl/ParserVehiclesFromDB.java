package Project.Classes.Infrastructure.dto.impl;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.ParserVehiclesInterface;
import Project.Classes.Infrastructure.dto.service.EngineService;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.TypesService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import lombok.Getter;

@Getter
public class ParserVehiclesFromDB implements ParserVehiclesInterface {
    @Autowired
    private TypesService typesService;
    @Autowired
    private RentsService rentsService;
    @Autowired
    private VehiclesService vehiclesService;
    @Autowired
    private EngineService engineService;

}
