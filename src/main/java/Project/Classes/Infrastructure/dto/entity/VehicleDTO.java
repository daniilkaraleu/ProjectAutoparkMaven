package Project.Classes.Infrastructure.dto.entity;


import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import lombok.*;

@ToString
@Table(name = "vehicles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    @ID(name = "id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "registrationNumber", unique = true)
    private String registrationNumber;
    @Column(name = "mass")
    private Double mass;
    @Column(name = "mileage")
    private Double mileage;
    @Column(name = "yearOfManufacture")
    private Integer yearOfManufacture;
    @Column(name = "tankVolume")
    private Double tankVolume;
    @Column(name = "type")
    private Long type;
    @Column(name = "engine")
    private String engine;
    @Column(name = "color")
    private String color;

    private String typeName;
    private double taxCoefficient;
    private double engineTaxCoefficient;
    private double per100km;
    private double maxKm;
    private double tax;
    private double income;
    private double rentIncome;
    private Boolean wasBroken;
    private Boolean fixed;
}
