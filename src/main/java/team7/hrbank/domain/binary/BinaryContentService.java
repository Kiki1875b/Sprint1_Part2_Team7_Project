package team7.hrbank.domain.binary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team7.hrbank.domain.binary.dto.BinaryContentDto;
import team7.hrbank.domain.binary.dto.BinaryMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BinaryContentService {

    private final BinaryContentRepository binaryContentRepository;
    private final LocalBinaryContentStorage localBinaryContentStorage;
    private final BinaryMapper binaryMapper;

    // 여러개 저장시 List 자료구조 사용
    // 호출하는 레이어에서 할 것 (직원 레이어)
    // Optional<BinaryContentDto> binaryContentDto = binaryMapper.convertFileToBinaryContent(file);
    // binaryContentDto.ifPresent((dto)->{ BinaryContentService.save ~~ });
    // + 확장자 체크 (동영상, pdf 등은 프로필로 불가능)
    public BinaryContent save(BinaryContentDto dto) {
        BinaryContent savedBinaryContent = binaryContentRepository.save(binaryMapper.toEntity(dto));
        localBinaryContentStorage.put(dto.bytes(), savedBinaryContent.getId());
        return savedBinaryContent;
    }

    public BinaryContent findById(Long id) {
        return binaryContentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Binary content not found with id: " + id));
    }

    // 부서별 프로필 조회 시
    public List<BinaryContent> findAll(List<Long> ids) {
        return binaryContentRepository.findAllById(ids);
    }
}
