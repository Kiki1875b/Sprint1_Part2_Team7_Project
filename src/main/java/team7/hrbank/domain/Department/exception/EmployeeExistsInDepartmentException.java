package team7.hrbank.domain.Department.exception;

public class EmployeeExistsInDepartmentException extends RuntimeException {
    public EmployeeExistsInDepartmentException(String message) {
        super(message);
    }
}
