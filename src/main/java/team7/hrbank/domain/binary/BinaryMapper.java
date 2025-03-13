package team7.hrbank.domain.binary;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.mapstruct.MappingInheritanceStrategy.*;


@MapperConfig(mappingInheritanceStrategy = AUTO_INHERIT_ALL_FROM_CONFIG)
@Mapper(componentModel = "spring")
public interface BinaryMapper {

    default Optional<BinaryContentDto> convertFileToBinaryContent(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(new BinaryContentDto(file.getName(), file.getOriginalFilename(), file.getSize(), file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    BinaryContent toEntity(BinaryContentDto binaryContentDtoSave);

    // 필요할 떄 ㄱ
    BinaryContentDto toDto(BinaryContent binaryContent);
}

