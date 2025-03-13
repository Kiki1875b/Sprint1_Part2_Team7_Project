package team7.hrbank.domain.binary;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import team7.hrbank.domain.employee.Employee;
import team7.hrbank.domain.employee.EmployeeRepository;

@Controller
@RequiredArgsConstructor
public class BinaryController {

    private final LocalBinaryContentStorage localBinaryContentStorage;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/api/files/{id}/download")
    public ResponseEntity<Resource> downLoad(@PathVariable Long id) {
        Employee employee = employeeRepository.findByBinaryContent_Id(id)
                .orElseThrow(() -> new RuntimeException("No employee found with binary content id: " + id));
        return localBinaryContentStorage.download(id,employee);
    }
}
