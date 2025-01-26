package com.rakesh.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.rakesh.departmentservice.dto.DepartmentDto;
import com.rakesh.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department service - Department Controller",
        description = "Department Controller exposes Rest Endpoint for Department service"
)
@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(
            summary = "Save department",
            description = "Rest Api to save department"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get department",
            description = "Rest Api to fetch department"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Parameter(
            name = "department-code",
            description = "Department code as string value",
            required = true,
            example = "1"
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
