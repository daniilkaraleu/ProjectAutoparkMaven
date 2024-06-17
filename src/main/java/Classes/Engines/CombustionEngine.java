package Classes.Engines;

public class CombustionEngine extends Engine{
    private double fuelTankCapacity;
    private double fuelConsumptionPer100;
    private double engineCapacity;

    public CombustionEngine(double taxPerMonth, String name, double fuelTankCapacity, double fuelConsumptionPer100, double engineCapacity) {
        super(taxPerMonth, name);
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumptionPer100 = fuelConsumptionPer100;
        this.engineCapacity = engineCapacity;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public double getFuelConsumptionPer100() {
        return fuelConsumptionPer100;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public void setFuelConsumptionPer100(double fuelConsumptionPer100) {
        this.fuelConsumptionPer100 = fuelConsumptionPer100;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public double getMaxKilometers() {
        return fuelTankCapacity * 100 / fuelConsumptionPer100;
    }
}
