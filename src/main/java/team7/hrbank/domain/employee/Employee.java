package team7.hrbank.domain.employee;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import team7.hrbank.domain.base.BaseEntity;
import team7.hrbank.domain.binary.BinaryContent;

import java.time.Instant;

import static jakarta.persistence.FetchType.*;

@Entity @Getter @Setter
@Table(name = "employees")
public class Employee extends BaseEntity {

    private String name;
    private String email;
    private String jobTitle;
    private Instant hireDate;
    private EmployStatus status;

    @ManyToOne(fetch = LAZY)
    private Department department;

    @OneToOne(fetch = LAZY)
    private BinaryContent binaryContent;
}
