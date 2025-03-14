package team7.hrbank.domain.employee.repository;

import team7.hrbank.domain.employee.entity.Employee;
import team7.hrbank.domain.employee.entity.EmployeeStatus;

import java.time.LocalDate;
import java.util.List;

public interface CustomEmployeeRepository {
    List<Employee> findEmployees(String nameOrEmail,
                                 String employeeNumber,
                                 String departmentName,
                                 String position,
                                 LocalDate hireDateFrom,
                                 LocalDate hireDateTo,
                                 EmployeeStatus status,
                                 Long idAfter,
                                 String cursor,
                                 int size,
                                 String sortField,
                                 String sortDirection
    );

    long totalCountEmployee(String nameOrEmail,
                                      String employeeNumber,
                                      String departmentName,
                                      String position,
                                      LocalDate hireDateFrom,
                                      LocalDate hireDateTo,
                                      EmployeeStatus status);
}
