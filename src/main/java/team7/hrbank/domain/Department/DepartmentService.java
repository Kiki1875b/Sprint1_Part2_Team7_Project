package team7.hrbank.domain.Department;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.hrbank.domain.Department.dto.DepartmentCreateRequest;
import team7.hrbank.domain.Department.dto.DepartmentRespons;
import team7.hrbank.domain.Department.dto.DepartmentUpdateRequest;
import team7.hrbank.domain.Department.exception.DepartmentAlreadyExistsException;
import team7.hrbank.domain.Department.exception.DepartmentNotFoundException;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

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
}
