package Classes.Engines;

public class DieselEngine extends CombustionEngine{
    public DieselEngine( double engineCapacity, double fuelConsumptionPer100,  double fuelTankCapacity) {
        super(1.2d, "Diesel", fuelTankCapacity, fuelConsumptionPer100, engineCapacity);
    }
}
