package com.rakesh.employeeservice.service;

import com.rakesh.employeeservice.dto.APIResponseDto;
import com.rakesh.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
