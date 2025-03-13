package team7.hrbank.domain.employee.dto;

import java.util.List;

public record CursorPageResponseEmployeeDto(
        List<EmployeeDto> content,  // 페이지 내용
        String nextCursor,          // 다음 페이지 커서
        Long nextIdAfter,           // 마지막 요소의 ID
        int size,                   // 페이지 크기
        Long totalElements,         // 총 요소 수
        boolean hasNext             // 다음 페이지 여부
) {
}
