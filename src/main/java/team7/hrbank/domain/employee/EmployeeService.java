package team7.hrbank.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.hrbank.domain.employee.dto.EmployeeCreateRequest;
import team7.hrbank.domain.employee.dto.EmployeeDto;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // 직원 등록
    public EmployeeDto create(EmployeeCreateRequest request) {

        // TODO: ChangeLog에 memo 저장

        // 사원번호 생성
        int year = request.hireDate().getYear();   // 입사 연도
        long count = employeeRepository.countByHireDateYear(year);  // 해당 연도 입사자 수
        String employeeNumber = String.format("EMP-%d-%03d", year, count + 1);  // 최종 사원번호

        // Employee 생성
        Employee employee = new Employee(employeeNumber, request.name(), request.email(), request.position(), request.hireDate());

        // repository로 저장
        // 엔티티 미완성으로 저장 로직 주석 처리
//        employeeRepository.save(employee);

        // employeeDto로 반환
        return EmployeeDto.fromEntity(employee);
    }

    // 직원 목록 조회

    // 직원 상세 조회

    // 직원 수정

    // 직원 삭제
}
