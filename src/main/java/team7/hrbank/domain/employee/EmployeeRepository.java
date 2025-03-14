package team7.hrbank.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 특정 연도에 입사한 직원 수 조회
    @Query("SELECT COUNT(e) FROM Employee e WHERE YEAR(e.hireDate) = :year")
    long countByHireDateYear(@Param("year") int year);
}
