package team7.hrbank.domain.Department;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.hrbank.domain.Department.dto.DepartmentCreateRequest;
import team7.hrbank.domain.Department.dto.DepartmentRespons;
import team7.hrbank.domain.Department.dto.DepartmentUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    /*
    @PostMapping("/test100Departments")
    public ResponseEntity<Void> createTest100Department() {
        for(int i = 1; i <= 100; i++) {
            departmentService.createDepartment(new DepartmentCreateRequest("부서"+i, "인생넘힘들다", ))
        }
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", "/departments")
                .build();
    }*/

    // 부서 등록 API _ 등록 후 해당 부서 상세화면으로 리다이렉트
    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentCreateRequest requestDto) {
        DepartmentRespons responseDto = departmentService.createDepartment(requestDto);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", "/departments/"+responseDto.id())
                .build();
    }

    // 부서 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentRespons> updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentUpdateRequest requestDto) {
        DepartmentRespons responseDto = departmentService.updateDepartment(id, requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    // 부서 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long id) {
        //departmentService.deleteDepartment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("부서가 삭제되었습니다");
    }

    //부서 목록 조회 API
    @GetMapping
    public ResponseEntity<List<DepartmentRespons>> getDepartments(
        @RequestParam(name = "nameOrDescription", required = false) String nameOrDescription, //입력안할시 null들어가서 전부 조회
        @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
        @RequestParam(name = "pageSize", defaultValue = "30") int pageSize, //한페이지당 보여질 페이지 수
        @RequestParam(name = "lastId", required = false) Integer lastId // 이전 페이지의 마지막 id
    ){
        List<DepartmentRespons> departmentDtoList = departmentService.getDepartments(nameOrDescription, pageSize, lastId, sortDirection);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentDtoList);

        //todo 마지막 번호 반환하는 코드 추가
    }

    //부서 상세 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentRespons> getDepartmentDetail(@PathVariable("id") Long id) {
        DepartmentRespons responseDto = departmentService.getDepartment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }
}
