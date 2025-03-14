package team7.hrbank.domain.Department;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team7.hrbank.domain.Department.dto.DepartmentCreateRequest;
import team7.hrbank.domain.Department.dto.DepartmentRespons;
import team7.hrbank.domain.Department.dto.DepartmentUpdateRequest;
import team7.hrbank.domain.Department.exception.DepartmentAlreadyExistsException;
import team7.hrbank.domain.Department.exception.DepartmentNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    //private final EmployeeRepository employeeRepository;

    @Transactional
    public DepartmentRespons createDepartment(DepartmentCreateRequest requestDto) {
        // 부서 이름 중복 체크
        if (departmentRepository.existsByName(requestDto.name())) {
            throw new DepartmentAlreadyExistsException("이미 존재하는 부서 이름입니다.");
        }
        // 엔티티 생성
        Department department = new Department(
                requestDto.name(),
                requestDto.description(),
                requestDto.establishedDate()
        );
        // 저장
        departmentRepository.save(department);
        // 저장된 데이터를 DTO로 변환 후 반환
        return new DepartmentRespons(department);
    }


    // 부서 수정 메서드
    @Transactional
    public DepartmentRespons updateDepartment(Long id, DepartmentUpdateRequest requestDto) {
        // 부서 이름 중복 체크
        if (departmentRepository.existsByName(requestDto.name())) {
            throw new DepartmentAlreadyExistsException("이미 존재하는 부서 이름입니다.");
        }
        // 기존 부서 조회
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("부서가 존재하지 않습니다."));
        // 부서 수정
        department.update(requestDto.name(), requestDto.description(), requestDto.establishedDate());
        // 수정된 부서 저장
        departmentRepository.save(department);

        return new DepartmentRespons(department);
    }

    /*
    mployeeRepository 이용하므로 우선 주석처리
    //부서 삭제 메서드
    @Transactional
    public void deleteDepartment(Long id) {
        // 기존 부서 조회
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("부서가 존재하지 않습니다."));
        //부서 내 소속직원 존재여부 체크
        if (employeeRepository.existByDepartment(department.getId())){
            throw  new EmployeeExistsInDepartmentException("소속된 직원이 존재하는 부서는 삭제할 수 없습니다. 직원 소속 변경 후 다시 시도해주세요.");
        }
        //부서 삭제
        departmentRepository.delete(department);
    }
    */

    //부서 조회 메서드 (페이지 사이즈 기본 30개, 이름과 설명으로 필터링)
    public List<DepartmentRespons> getDepartments(String nameOrDescription, int pageSize, Integer lastId, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(Sort.Direction.DESC, "id")
                : Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, pageSize, sort);

        Page<Department> departments;
        if (lastId == null){
            departments = departmentRepository.findByCriteria(nameOrDescription, pageable);
        }else{
            departments = departmentRepository.findByCriteriaWithLastId(nameOrDescription, lastId, pageable);
        }

        List<DepartmentRespons> departmentDtoList = departments.stream()
                .map(department -> new DepartmentRespons(department))
                .collect(Collectors.toList());

        return departmentDtoList;
    }

    //부서 단건 조회 메서드
    public DepartmentRespons getDepartment(Long id) {
        // 기존 부서 조회
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("부서 코드는 필수입니다."));
        return new DepartmentRespons(department);
    }
}
