package Project.Classes.Infrastructure.dto.entity;


import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import lombok.*;

@ToString
@Table(name = "rents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rents {
    @ID(name = "id")
    private Long id;
    @Column(name = "vehicleId")
    private Long vehicleId;
    @Column(name = "rentDate")
    private String rentDate;
    @Column(name = "rentCost")
    private Double rentCost;
}
