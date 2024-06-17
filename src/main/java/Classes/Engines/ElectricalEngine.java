package Classes.Engines;

public class ElectricalEngine extends Engine{
    private double batteryCharge;
    private double electricityConsumption;

    public ElectricalEngine(double electricityConsumption, double batteryCharge) {
        super(0.1, "Electrical");
        this.batteryCharge = batteryCharge;
        this.electricityConsumption = electricityConsumption;
    }

    public void setBatteryCharge(double batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public double getBatteryCharge() {
        return batteryCharge;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    @Override
    public double getTaxPerMonth() {
        return super.getTaxPerMonth();
    }

    @Override
    public double getMaxKilometers() {
        return batteryCharge / electricityConsumption;
    }
}
