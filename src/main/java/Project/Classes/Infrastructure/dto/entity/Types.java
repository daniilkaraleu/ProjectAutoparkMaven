package Project.Classes.Infrastructure.dto.entity;

import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import lombok.*;

@Table(name = "types")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Types {
    @ID(name = "id")
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "coefTaxes")
    private Double coefTaxes;
}
