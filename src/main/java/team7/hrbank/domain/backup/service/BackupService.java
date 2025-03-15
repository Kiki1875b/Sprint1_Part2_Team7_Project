package team7.hrbank.domain.backup.service;

import java.net.InetAddress;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team7.hrbank.common.dto.PageResponse;
import team7.hrbank.domain.backup.dto.BackupDto;
import team7.hrbank.domain.backup.dto.BackupListRequestDto;
import team7.hrbank.domain.backup.entity.BackupStatus;

public interface BackupService {

  PageResponse<BackupDto> findBackupsOfCondition(
      BackupListRequestDto dto,
      int size,
      String sortField,
      String sortDirection
  );

  BackupDto startBackup();

  BackupDto findLatestBackupByStatus(BackupStatus status);
}
