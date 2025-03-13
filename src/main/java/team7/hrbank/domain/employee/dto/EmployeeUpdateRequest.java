package team7.hrbank.domain.employee.dto;

import team7.hrbank.domain.employee.EmployeeStatus;

public record EmployeeUpdateRequest(
        String name,
        String email,
        Long departmentId,
        String position,
        String hireDate,
        EmployeeStatus status,
        String memo
) {
}
