package team7.hrbank.domain.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team7.hrbank.domain.employee.dto.EmployeeCreateRequest;
import team7.hrbank.domain.employee.dto.EmployeeUpdateRequest;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // 직원 등록
    @PostMapping
    public ResponseEntity<String> create(@RequestBody EmployeeCreateRequest employee,
                                         @RequestParam(required = false) MultipartFile profile) {
        if (profile != null) {
            // 이미지 사진 처리 로직
        }

        // 직원 생성 로직

        // TODO: ResponseEntity<EmployeeDto> 반환으로 수정
        return ResponseEntity.ok("직원 등록 성공");
    }

    // 직원 목록 조회
    @GetMapping
    public ResponseEntity<String> read(@RequestParam(required = false) String nameOrEmail,
                                       @RequestParam(required = false) String employeeNumber,
                                       @RequestParam(required = false) String departmentName,
                                       @RequestParam(required = false) String position,
                                       @RequestParam(required = false) String hireDateFrom,
                                       @RequestParam(required = false) String hireDateTo,
                                       @RequestParam(required = false) EmployeeStatus status,
                                       @RequestParam(required = false) Long idAfter,
                                       @RequestParam(required = false) String cursor,
                                       @RequestParam(defaultValue = "25") int size,
                                       @RequestParam(defaultValue = "name") String sortField,
                                       @RequestParam(defaultValue = "asc") String sortDirection) {
        
        // 직원 목록 조회 로직

        // TODO: ResponseEntity<CursorPageResponseEmployeeDto> 반환으로 수정
        return ResponseEntity.ok("직원 목록 조회 성공");
    }

    // 직원 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<String> readById(@PathVariable Long id) {

        // 조회 로직

        // TODO: ResponseEntity<EmployeeDto> 반환으로 수정
        return ResponseEntity.ok("직원 상세 조회 성공");
    }

    // 직원 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody EmployeeUpdateRequest employee,
                                         @RequestParam(required = false) MultipartFile profile) {
        if (profile != null) {
            // 이미지 사진 처리 로직
        }

        // 직원 수정 로직

        // TODO: ResponseEntity<EmployeeDto> 반환으로 수정
        return ResponseEntity.ok("직원 수정 성공");
    }

    // 직원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        // 삭제 로직

        return ResponseEntity.noContent().build();
    }
}
