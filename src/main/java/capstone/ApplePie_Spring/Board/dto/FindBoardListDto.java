package capstone.ApplePie_Spring.Board.dto;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class FindBoardListDto {

    //private String nickname;
    private Long id;
    private String title;
    private String content;
    private int view_count;
    private String file;

    @Builder
    public FindBoardListDto(Board board, Optional<File> file) {
        this.id = board.getId();
        //this.nickname = board.getUser().getNickname();

        this.title = board.getTitle();
        this.view_count = board.getView_count();
        if (board.getFiles().size() == 0) {
            this.file = null;
        }
        else {
            this.file = board.getFiles().get(0).getUrl();
        }

        List<File> fileList = board.getFiles();
        if (board.getContent().length() > 16) {
            this.content = board.getContent().substring(0,14); // 15글자
        }
        else {
            this.content = board.getContent();
        }

        if (file.isEmpty()) {
            this.file = null;
        }
        else {
            this.file = file.get().getUrl();
        }
    }
}
