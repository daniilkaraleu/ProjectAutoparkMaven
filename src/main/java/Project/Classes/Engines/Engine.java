package Project.Classes.Engines;

import Project.Classes.Engines.interfaces.Startable;

abstract public class Engine implements Startable {
    private double taxPerMonth;
    private String name;

    public Engine(double taxPerMonth, String name) {
        this.taxPerMonth = taxPerMonth;
        this.name = name;
    }

    @Override
    public double getTaxPerMonth() {
        return taxPerMonth;
    }

    public String getName() {
        return name;
    }

    public void setTaxPerMonth(double taxPerMonth) {
        this.taxPerMonth = taxPerMonth;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  taxPerMonth + "," + name ;
    }
}
