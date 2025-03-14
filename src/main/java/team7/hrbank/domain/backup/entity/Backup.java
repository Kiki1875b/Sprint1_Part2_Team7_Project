package team7.hrbank.domain.backup.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Table(name = "backup_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Backup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // TODO : FileId 추가 -> BinaryContents 엔티티 완료시
  @Column(name = "operator", nullable = false, updatable = false)
  private String worker;

  @CreatedDate
  @Column(name = "start_time", nullable = false, updatable = false)
  private Instant startedAt;

  @Column(name = "end_time")
  private Instant endedAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private BackupStatus status; // Mapper 에서 Default 설정 - 진행중

  public void addOperator(String worker) {
    this.worker = worker;
  }

  public void endBackup() {
    endedAt = Instant.now();
  }
}
