package team7.hrbank.domain.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name); // 부서 이름 중복 여부 확인
}
