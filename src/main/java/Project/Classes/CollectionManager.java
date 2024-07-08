package Project.Classes;

import Project.Classes.Infrastructure.dto.entity.MapperForDB;
import Project.Classes.Collections.RentCollection;
import Project.Classes.Collections.TypesCollection;
import Project.Classes.Collections.VehicleCollection;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.interfaces.Manager;
import Project.Classes.Infrastructure.dto.impl.ParserVehiclesFromDB;
import lombok.Getter;

public class CollectionManager implements Manager {
    @Getter
    private VehicleCollection vehicleCollection;
    @Getter
    private TypesCollection typeCollection;
    @Getter
    private RentCollection rentCollection;
//    @Autowired
//    private ParseVehicleFromFile parser;
    @Autowired
    private ParserVehiclesFromDB parserFromDB;
    @Autowired
    private MapperForDB mapperForDB;



    public CollectionManager(){
        vehicleCollection = new VehicleCollection();

        typeCollection = new TypesCollection();

        rentCollection = new RentCollection();
    }

    @InitMethod
    public void init() {
        parserFromDB.getTypesService()
                .getAll()
                .stream()
                .map(MapperForDB::createVehicleType)
                .forEach(vehicleType -> typeCollection.insert(vehicleType));
        parserFromDB.getVehiclesService()
                .getAll()
                .stream()
                .map(vehicles -> MapperForDB.createVehicle(vehicles, parserFromDB.getRentsService().getAll()))
                .forEach(vehicle -> vehicleCollection.insert(vehicle));
        parserFromDB.getRentsService()
                .getAll()
                .stream()
                .map(MapperForDB::createRent)
                .forEach(rent -> rentCollection.insert(rent));

    }
}
