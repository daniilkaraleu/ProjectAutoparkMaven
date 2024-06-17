package Classes;

import Classes.Collections.ObjectCreator.CreateRent;
import Classes.Collections.ObjectCreator.CreateType;
import Classes.Collections.ObjectCreator.CreateVehicle;
import Classes.Collections.ObjectCreator.ParseVehicleFromFile;
import Classes.Collections.RentCollection;
import Classes.Collections.TypesCollection;
import Classes.Collections.VehicleCollection;
import Infrastructure.core.annotations.Autowired;
import Infrastructure.core.annotations.InitMethod;
import Classes.interfaces.Manager;
import lombok.Getter;

public class CollectionManager implements Manager {
    private static final String TYPES_CSV = "types";
    private static final String VEHICLES_CSV = "vehicles";
    private static final String RENTS_CSV = "rents";
    @Getter
    private final Classes.Collections.VehicleCollection vehicleCollection;
    @Getter
    private final TypesCollection typesCollection;
    @Getter
    private final RentCollection rentCollection;
    @Autowired
    private ParseVehicleFromFile parser;


    public CollectionManager(){
        this.typesCollection = new TypesCollection();
        this.rentCollection = new RentCollection();
        this.vehicleCollection = new VehicleCollection();
    }

    @InitMethod
    public void init() {
        parser.loadCollection(TYPES_CSV, new CreateType()).forEach(typesCollection::insert);
        parser.loadCollection(RENTS_CSV, new CreateRent()).forEach(rentCollection::insert);
        parser.loadCollection(VEHICLES_CSV, new CreateVehicle(this.typesCollection, this.rentCollection)).forEach(vehicleCollection::insert);

    }
}
