package Project.Classes.Infrastructure.dto.entity;

import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import lombok.*;

@ToString
@Table(name = "engines")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineDTO {
    @ID(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "engineCapacity", nullable = false)
    private Double engineCapacity;
    @Column(name = "fuelConsumption")
    private Double fuelConsumption;
    @Column(name = "tankCapacity")
    private Double tankCapacity;
}
