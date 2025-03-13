package team7.hrbank.domain.backup.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team7.hrbank.domain.base.BaseEntity;

@Entity
@Getter
@Table(name = "BACKUP_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Backup {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // TODO : FileId 추가 -> BinaryContents 엔티티 완료시 

  @Column(name = "operator")
  private String operator;

  @Column(name = "start_time")
  private Instant startTime;

  @Column(name = "end_time")
  private Instant endTime;

  @Enumerated(EnumType.STRING)
  private BackupStatus status;
}
