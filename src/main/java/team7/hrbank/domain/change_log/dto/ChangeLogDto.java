package team7.hrbank.domain.change_log.dto;

import java.time.LocalDateTime;
import team7.hrbank.domain.change_log.entity.ChangeLogType;

public record ChangeLogDto(
    Long id,
    String employeeNumber,
    ChangeLogType type,
    String memo,
    String ipAddress,
    LocalDateTime at
) {
}
