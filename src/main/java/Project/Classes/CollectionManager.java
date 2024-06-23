package Project.Classes;

import Project.Classes.Collections.ObjectCreator.CreateType;
import Project.Classes.Collections.ObjectCreator.ParseVehicleFromFile;
import Project.Classes.Collections.RentCollection;
import Project.Classes.Collections.TypesCollection;
import Project.Classes.Collections.VehicleCollection;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.annotations.InitMethod;
import Project.Classes.interfaces.Manager;
import Project.Classes.Infrastructure.dto.entity.Rents;
import Project.Classes.Infrastructure.dto.entity.Types;
import Project.Classes.Infrastructure.dto.entity.Vehicles;
import Project.Classes.Infrastructure.dto.impl.ParserVehiclesFromDB;
import lombok.Getter;

import java.util.List;

public class CollectionManager implements Manager {
    private static final String TYPES_CSV = "types";
    private static final String VEHICLES_CSV = "vehicles";
    private static final String RENTS_CSV = "rents";
    @Getter
    private  List<Vehicles> vehiclesCollection;
    @Getter
    private  List<Types> typesCollection;
    @Getter
    private  List<Rents> rentsCollection;



    @Getter
    private VehicleCollection vehicleCollection;
    @Getter
    private TypesCollection typeCollection;
    @Getter
    private RentCollection rentCollection;
    @Autowired
    private ParseVehicleFromFile parser;
    @Autowired
    private ParserVehiclesFromDB parserFromDB;
    @Autowired
    private CreateType createType;



    public CollectionManager(){
        vehicleCollection = new VehicleCollection();

        typeCollection = new TypesCollection();

        rentCollection = new RentCollection();
    }

    @InitMethod
    public void init() {
//        parserFromDB.getVehiclesService().getAll().forEach(vehicleCollection::insert);
//        this.typesCollection = parserFromDB.getTypesService().getAll();
//        this.vehiclesCollection = parserFromDB.getVehiclesService().getAll();
//        this.rentsCollection = parserFromDB.getRentsService().getAll();

        parserFromDB.getTypesService()
                .getAll()
                .stream()
                .map(CreateType::createVehicleType)
                .forEach(vehicleType -> typeCollection.insert(vehicleType));
        parserFromDB.getVehiclesService()
                .getAll()
                .stream()
                .map(CreateType::createVehicle)
                .forEach(vehicle -> vehicleCollection.insert(vehicle));
        parserFromDB.getRentsService()
                .getAll()
                .stream()
                .map(CreateType::createRent)
                .forEach(rent -> rentCollection.insert(rent));

//        parser.loadCollection(TYPES_CSV, new CreateType()).forEach(typesCollection::insert);
//        parser.loadCollection(RENTS_CSV, new CreateRent()).forEach(rentCollection::insert);
//        parser.loadCollection(VEHICLES_CSV, new CreateVehicle(this.typesCollection, this.rentCollection)).forEach(vehicleCollection::insert);

    }
}
