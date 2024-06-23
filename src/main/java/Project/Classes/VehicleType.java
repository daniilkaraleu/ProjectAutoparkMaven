package Project.Classes;

import Project.Classes.interfaces.Displayable;

public class VehicleType implements Displayable {

    private String typeName;
    private double taxCoefficient;
    private Long typeId;

    VehicleType() {
    }

    public VehicleType(Long typeId ,String typeName, double taxCoefficient) {
        this.typeId = typeId;
        this.taxCoefficient = taxCoefficient;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setType(String typeName) {
        this.typeName = typeName;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display(){
        System.out.println("taxCoefficient = " + taxCoefficient);
        System.out.println("typeName = " + typeName);
    }
    public String toString() {
        return typeId + "     " + typeName + "      " + taxCoefficient;
    }
}