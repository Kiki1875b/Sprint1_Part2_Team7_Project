package team7.hrbank.domain.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team7.hrbank.domain.employee.Employee;
import team7.hrbank.domain.employee.EmployeeStatus;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EmployeeDto {
    Long id;
    String name;
    String email;
    String employeeNumber;
    Long departmentId;
    String departmentName;
    String position;
    LocalDate hireDate;
    EmployeeStatus status;
    Long profileImageId;

    public static EmployeeDto fromEntity(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getEmployeeNumber(),
                1L,  // TODO: department 추가 후 수정
                "임시부서",       // TODO: department 추가 후 수정
                employee.getPosition(),
                employee.getHireDate(),
                employee.getStatus(),
                1L  // TODO: binaryContent 추가 후 수정
        );
    }
}
