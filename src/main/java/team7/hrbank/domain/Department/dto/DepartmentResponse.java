package team7.hrbank.domain.Department.dto;

import team7.hrbank.domain.Department.Department;
import java.time.LocalDate;

public record DepartmentResponse (
        Integer id,
        String name,
        String description,
        LocalDate establishedDate
) {
    public DepartmentResponse(Department department) {
        this(department.getId(), department.getName(), department.getDescription(), department.getEstablishedDate());
    }
}
