package Project.Classes;

import Project.Classes.Infrastructure.dto.annotations.Column;
import Project.Classes.Infrastructure.dto.annotations.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Order {
    private Vehicle vehicle;
    private Long id;
    private Integer CV_joint;
    private Integer axis;
    private Integer oil;
    private Integer timingBelt;
    private Integer filter;
    private Integer sleeve;
    private Integer shaft;
    private Integer sparkPlug;

}
