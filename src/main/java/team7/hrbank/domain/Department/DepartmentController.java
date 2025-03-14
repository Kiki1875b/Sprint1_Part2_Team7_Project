package team7.hrbank.domain.Department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.hrbank.domain.Department.dto.DepartmentCreateRequest;
import team7.hrbank.domain.Department.dto.DepartmentRespons;
import team7.hrbank.domain.Department.dto.DepartmentUpdateRequest;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // 부서 등록 API
    @PostMapping
    public ResponseEntity<DepartmentRespons> createDepartment(@RequestBody DepartmentCreateRequest requestDto) {
        DepartmentRespons responseDto = departmentService.createDepartment(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 부서 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentRespons> updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateRequest requestDto) {
        DepartmentRespons departmentRespons = departmentService.updateDepartment(id, requestDto);
        return ResponseEntity.ok(departmentRespons);
    }
}
