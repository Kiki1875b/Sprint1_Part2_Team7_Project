package team7.hrbank.domain.Department.dto;

import team7.hrbank.domain.Department.Department;
import java.time.LocalDate;

public record DepartmentRespons(
        Long id,
        String name,
        String description,
        LocalDate establishedDate
) {
    public DepartmentRespons(Department department) {
        this(department.getId(), department.getName(), department.getDescription(), department.getEstablishedDate());
    }
}
