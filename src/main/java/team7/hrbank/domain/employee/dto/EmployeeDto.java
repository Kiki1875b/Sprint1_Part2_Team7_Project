package team7.hrbank.domain.employee.dto;

import team7.hrbank.domain.employee.EmployeeStatus;

public record EmployeeDto(
        Long id,
        String name,
        String email,
        String employeeNumber,
        Long departmentId,
        String departmentName,
        String position,
        String hireDate,
        EmployeeStatus status,
        Long profileImageId
) {
}
