package team7.hrbank.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team7.hrbank.domain.binary.BinaryContent;
import team7.hrbank.domain.binary.BinaryContentService;
import team7.hrbank.domain.binary.dto.BinaryContentDto;
import team7.hrbank.domain.employee.dto.EmployeeCreateRequest;
import team7.hrbank.domain.employee.dto.EmployeeDto;
import team7.hrbank.domain.employee.dto.EmployeeUpdateRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    // 의존성 주입
    private final EmployeeRepository employeeRepository;
    private final BinaryContentService binaryContentService;

    // 직원 등록
    public EmployeeDto create(EmployeeCreateRequest request, MultipartFile profile) {

        // TODO: ChangeLog에 memo 저장

        // 사원번호 생성
        int year = request.hireDate().getYear();   // 입사 연도
        long count = employeeRepository.countByHireDateYear(year);  // 해당 연도 입사자 수
        String employeeNumber = String.format("EMP-%d-%03d", year, count + 1);  // 최종 사원번호

        // 프로필 사진
        BinaryContent binaryContent = null;
//        if (profile != null) {
//            binaryContent = profileProcess(profile);
//        }

        // Employee 생성
        Employee employee = new Employee(binaryContent, employeeNumber, request.name(), request.email(), request.position(), request.hireDate());

        // DB 저장
        employeeRepository.save(employee);

        // employeeDto로 반환
        return EmployeeDto.fromEntity(employee);
    }

    // 직원 목록 조회

    // 직원 상세 조회

    // 직원 수정
    public EmployeeDto updateById(Long id, EmployeeUpdateRequest request, MultipartFile profile) {

        // TODO: ChangeLog에 수정 이력 저장

        Employee employee = employeeRepository.findById(id).orElseThrow();  // TODO: 예외처리

        // TODO: departmentId 수정 로직 추가

        if (request.name() != null) {
            employee.updateName(request.name());
        }
        if (request.email() != null) {
            employee.updateEmail(request.email());
        }
        if (request.position() != null) {
            employee.updatePosition(request.position());
        }
        if (request.hireDate() != null) {
            employee.updateHireDate(request.hireDate());
        }
        if (request.status() != null) {
            employee.updateStatus(request.status());
        }
//        if (profile != null) {
//            BinaryContent binaryContent = profileProcess(profile);
//            employee.updateProfile(binaryContent);
//        }

        // DB 저장
        employeeRepository.save(employee);

        // employeeDto로 반환
        return EmployeeDto.fromEntity(employee);
    }

    // 직원 삭제

//    // 프로필 사진 가공
//    private BinaryContent profileProcess(MultipartFile profile) {
//        try {
//            String fileName = profile.getOriginalFilename();
//            String fileType = profile.getContentType();
//            Long fileSize = profile.getSize();
//            byte[] bytes = profile.getBytes();
//
//            BinaryContentDto binaryContentDto = new BinaryContentDto(fileName, fileType, fileSize, bytes);
//
//            return binaryContentService.save(binaryContentDto);
//
//        } catch (IOException e) {
//            throw new IllegalArgumentException("파일 변환 실패");
//        }
//    }
}
