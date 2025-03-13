package team7.hrbank.domain.binary;


import jakarta.persistence.*;
import lombok.*;
import team7.hrbank.domain.base.BaseEntity;


@EqualsAndHashCode(callSuper = true)
@ToString(of = {"fileName", "fileType", "fileSize"})
@Entity
@Table(name = "binary_contents")
@Getter @Setter
@NoArgsConstructor
public class BinaryContent extends BaseEntity { // 임시로 BaseEntity 상속(업데이트 미적용)

    private String fileName;
    private String fileType;
    private Long fileSize;
}
