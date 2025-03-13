package team7.hrbank.domain.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import team7.hrbank.domain.base.BaseEntity;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {

    // TODO: Department, BinaryContent 엔티티 완료 시 departmentId, profileImageId 추가

    @Column(name = "employee_number", unique = true, nullable = false)
    private String employeeNumber;  // 사원번호

    @Column(name = "name", nullable = false)
    private String name;    // 이름

    @Column(name = "email", unique = true, nullable = false)
    private String email;   // 이메일

    @Column(name = "job_title", nullable = false)
    private String position;    // 직함

    @Column(name = "hire_date", nullable = false)
    private Instant hireDate;   // 입사일

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status;  // 상태(재직중, 휴직중, 퇴사)

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;  // 수정일
}
