package capstone.ApplePie_Spring.Board.dto;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
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

    public File toFile(Board board) {
        return File.builder()
                .fileDto(this)
                .board(board)
                .build();
    }
}
