package Project.Classes.Infrastructure.dto.impl;

import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.dto.ParserVehiclesInterface;
import Project.Classes.Infrastructure.dto.service.*;
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
    @Autowired
    private OrdersService ordersService;

}
