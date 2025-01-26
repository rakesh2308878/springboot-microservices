package com.rakesh.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
    description = "DepartmentDto Model Info"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @Schema(
            description = "Holds department id"
    )
    private Long id;
    @Schema(
            description = "Holds department name"
    )
    private String departmentName;
    @Schema(
            description = "Holds department description"
    )
    private String departmentDescription;
    @Schema(
            description = "Holds department code"
    )
    private String departmentCode;
}
