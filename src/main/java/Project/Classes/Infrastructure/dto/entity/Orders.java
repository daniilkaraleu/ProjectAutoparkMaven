package Project.Classes.Infrastructure.dto.entity;

import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import Project.Classes.Infrastructure.dto.annotations.Table;
import lombok.*;

@ToString
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @ID(name = "id")
    private Long id;
    @Column(name = "vehicleId")
    private Long vehicleId;
    @Column(name = "шрус")
    private Integer CV_joint;
    @Column(name = "ось")
    private Integer axis;
    @Column(name = "масло")
    private Integer oil;
    @Column(name = "ГРМ")
    private Integer timingBelt;
    @Column(name = "фильтр")
    private Integer filter;
    @Column(name = "втулка")
    private Integer sleeve;
    @Column(name = "вал")
    private Integer shaft;
    @Column(name = "свеча")
    private Integer sparkPlug;
}
