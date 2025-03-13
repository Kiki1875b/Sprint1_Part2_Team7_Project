package team7.hrbank.domain.binary;

public record BinaryContentDto(
        String fileName,
        String fileType,
        Long fileSize,
        byte[] bytes) {
}
