package team7.hrbank.domain.backup.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team7.hrbank.domain.backup.entity.Backup;
import team7.hrbank.domain.backup.entity.BackupStatus;

public interface BackupRepository extends JpaRepository<Backup, Long>, CustomBackupRepository {
  Optional<Backup> findFirstByStatusOrderByStartedAtDesc(BackupStatus status);

   Optional<Backup> findFirstByOrderByStartedAtDesc();
}
