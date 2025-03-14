package team7.hrbank.domain.backup.service;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team7.hrbank.common.dto.PageResponse;
import team7.hrbank.domain.backup.dto.BackupDto;
import team7.hrbank.domain.backup.entity.Backup;
import team7.hrbank.domain.backup.entity.BackupStatus;
import team7.hrbank.common.exception.BackupException;
import team7.hrbank.domain.backup.mapper.BackupMapper;
import team7.hrbank.domain.backup.repository.BackupRepository;

@Service
@RequiredArgsConstructor
public class BackupServiceImpl implements BackupService{

  private final BackupRepository backupRepository;
  private final BackupMapper backupMapper;

  // TODO : 최적화 필요
  @Override
  public PageResponse<BackupDto> findBackupsOfCondition(
      String worker,
      BackupStatus status,
      Instant startedAtFrom,
      Instant startedAtTo,
      Long idAfter,
      Instant cursor,
      int size,
      String sortField,
      String sortDirection
  ) {

    List<Backup> backups = backupRepository.findBackups(
        worker,
        status,
        startedAtFrom,
        startedAtTo,
        idAfter,
        cursor,
        size,
        sortField,
        sortDirection
    );

    if(backups.isEmpty()) {
      return new PageResponse<>(
          Collections.emptyList(),
          null,
          null,
          size,
          0,
          false
      );
    }

    List<Backup> nextPageCheck = backupRepository.findBackups(
        worker,
        status,
        startedAtFrom,
        startedAtTo,
        backups.get(backups.size() - 1).getId(),  // 마지막 요소의 ID를 커서로 사용
        null,  // 새로운 커서 기준 (추가 데이터 확인용)
        1,  // 다음 데이터가 있는지만 확인하기 위해 1개만 조회
        sortField,
        sortDirection
    );

    boolean hasNext = !nextPageCheck.isEmpty();
    Long nextIdAfter = backups.get(backups.size() - 1).getId();
    Instant nextCursor = backups.stream()
        .map(Backup::getStartedAt)
        .sorted(
            "DESC".equalsIgnoreCase(sortDirection)
            ? Comparator.reverseOrder() : Comparator.naturalOrder()
        ).findFirst()
        .orElse(null);

    return new PageResponse<>(
        backupMapper.fromEntityList(backups),
        nextCursor,
        nextIdAfter,
        size,
        0,
        hasNext
    );
  }

  @Override
  public BackupDto startBackup() {
    return null;
  }

  @Override
  public BackupDto findLatestBackupByStatus(BackupStatus status) {
    // 에러 처리 방식 논의
    Backup backup = backupRepository.findFirstByStatusOrderByStartedAtDesc(status)
        .orElseThrow(() -> new BackupException());

    return backupMapper.fromEntity(backup);
  }
}
