package capstone.ApplePie_Spring.Board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileDto {
    private String name;
    private long size;
    private String extension;

    @Builder
    public FileDto(String name, long size, String extension) {
        this.name = name;
        this.size = size;
        this.extension = extension;
    }
}
