package capstone.ApplePie_Spring.Board.dto;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FindBoardListDto {
    
    private Long id;
    private String title;
    private String content;
    private int view_count;
    private String file;

    @Builder
    public FindBoardListDto(Board board, List<File> fileList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.view_count = board.getView_count();
        if (board.getFiles().isEmpty()) {
            this.file = null;
        }
        else {
            this.file = board.getFiles().get(0).getUrl();
        }

        if (board.getContent().length() > 16) {
            this.content = board.getContent().substring(0,14); // 15글자
        }
        else {
            this.content = board.getContent();
        }

        if (fileList.size() < 1) {
            this.file = null;
        }
        else {
            this.file = fileList.get(0).getUrl();
        }
    }
}
