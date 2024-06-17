package Classes.Engines;

public class GasolineEngine extends CombustionEngine{
    public GasolineEngine(double engineCapacity, double fuelConsumptionPer100,  double fuelTankCapacity)  {
        super(1.1d, "Gasoline", fuelTankCapacity, fuelConsumptionPer100, engineCapacity);
    }
}
