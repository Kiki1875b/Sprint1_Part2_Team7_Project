package team7.hrbank.domain.binary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import team7.hrbank.domain.employee.Employee;

import javax.xml.transform.Source;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.*;

@Repository
public class LocalBinaryContentStorage {

    // 예외 자세한 처리는 나중에 ㄱ
    private final Path root;

    public LocalBinaryContentStorage(@Value("${hrbank.storage.local.root-path}") String root) {
        this.root = Paths.get(root);
        init();// 임시로
    }

    //    public void put(byte[] content, BinaryContent binaryContent) {
    public void put(byte[] content, Long id) {
        // 부서별 파일 이름 필요?? (나중에 의논 Cuz 부서별로 디렉토리 만들어서 저장? 부서에다 직원 ID 이렇게 커스텀하면 나중에 download 성능이 느려지거나 여러 파라미터 정보가 필요할 것)
        try {
            Files.write(resolvePath(id), content);
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    public ResponseEntity<Resource> download(Long id, Employee employee) {
        Path profilePath = resolvePath(id);
        String fileType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        try {
            String probedFileType = Files.probeContentType(profilePath);
            if (!probedFileType.isBlank()) {
                fileType = probedFileType;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION, "attachment; filename=[" + employee.getDepartment().getName() + "]" + "_" + employee.getId() + "." + fileType.split("/")[0]);
        headers.add(CONTENT_TYPE, fileType);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new FileSystemResource(profilePath));
    }

    /**
     * 편의
     */
    private Path resolvePath(Long id) {
        return root.resolve(id.toString());
    }
    //    private Path resolvePathForDownLoad(Employee employee) {
//        //파일 저장 위치 규칙 예시: {root}/[부서명]_[사원ID].jpg
//        // 이렇게 하면 불편한 점 : 부서정보, 타입정보, 사원정보, binaryContent정보가 필요함
    // 파라미터로 받아올게 많아져서 불편 예상
    // startwith 으로 필터링할 경우 전부 읽어오기 때문에 부담 예상
//        String departmentName = employee.getDepartment().getName();
//        Long id = employee.getId();
//        return root.resolve(binaryContent.getId().toString() + "." + binaryContent.getFileType());
//        return root.resolve("[" +departmentName + "]_" + id + ".jpg");
//    }

    //루트 디렉토리를 초기화합니다.
    //Bean이 생성되면 자동으로 호출되도록
    void init() {
        if (!Files.exists(root)) {
            try {
                Files.createDirectories(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Stream<Path> list = Files.list(root)) {
                list.forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
