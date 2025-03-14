package team7.hrbank.domain.backup.mapper;


import java.util.List;
import org.springframework.stereotype.Component;
import team7.hrbank.domain.backup.dto.BackupDto;
import team7.hrbank.domain.backup.entity.Backup;

@Component // TODO : 병합 후 Mapper 로 수정
public interface BackupMapper {
  BackupDto fromEntity(Backup backup);
  List<BackupDto> fromEntityList(List<Backup> backups);
}
