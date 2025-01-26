package com.rakesh.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.rakesh.employeeservice.dto.APIResponseDto;
import com.rakesh.employeeservice.dto.EmployeeDto;
import com.rakesh.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee service - Employee Controller",
        description = "Employee Controller exposes Rest Endpoint for Employee service"
)
@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(
            summary = "Save employee",
            description = "Rest Api to save employee"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get employee",
            description = "Rest Api to fetch employee"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = APIResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Parameter(
            name = "id",
            description = "Id as long value",
            required = true,
            example = "1"
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
