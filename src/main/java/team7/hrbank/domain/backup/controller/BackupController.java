package team7.hrbank.domain.backup.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team7.hrbank.common.dto.PageResponse;
import team7.hrbank.domain.backup.dto.BackupDto;
import team7.hrbank.domain.backup.dto.BackupListRequestDto;
import team7.hrbank.domain.backup.entity.BackupStatus;
import team7.hrbank.domain.backup.service.BackupService;
import team7.hrbank.domain.backup.service.BackupServiceImpl;

@RestController
@RequestMapping("/api/backups")
@RequiredArgsConstructor
public class BackupController {

  private final BackupService backupService;

  // 200, 400, 500
  @GetMapping
  public ResponseEntity<PageResponse<BackupDto>> getBackupList(
      @ModelAttribute BackupListRequestDto dto,
      @RequestParam(name = "size", required = false, defaultValue = "10") int size,
      @RequestParam(name = "sortField", required = false, defaultValue = "startedAt") String sortField,
      @RequestParam(name = "sortDirection", required = false, defaultValue = "DESC") String sortDirection
  ) {

    PageResponse<BackupDto> response = backupService.findBackupsOfCondition(
        dto, size, sortField, sortDirection
    );
    return ResponseEntity.ok(response);
  }

  // 200, 400, 409, 500
  @PostMapping
  public ResponseEntity<BackupDto> generateBackup() {
    BackupDto response = backupService.startBackup();
    return ResponseEntity.ok(response);
  }

  // 200, 400, 500
  @GetMapping("/latest")
  public ResponseEntity<BackupDto> getLatestBackup(
      @RequestParam(defaultValue = "COMPLETED", required = false, name = "status") BackupStatus status) {
    BackupDto response = backupService.findLatestBackupByStatus(status);
    return ResponseEntity.ok(response);
  }

}
