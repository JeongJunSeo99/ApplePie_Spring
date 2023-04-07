package capstone.ApplePie_Spring.Board.dto;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class FindOneBoardDto {

    private String nickname;
    private Long id;
    private String title;
    private String content;
    private int view_count;
    private Board.Category category;
    private List<String> files;

    @Builder
    public FindOneBoardDto(Board board, List<File> fileList) {
        this.id = board.getId();
        this.nickname = board.getUser().getNickname();
        this.title = board.getTitle();
        String findContent = board.getContent();
        this.content = board.getContent();
        this.view_count = board.getView_count();
        this.category = board.getCategory();

        files = new ArrayList<>();
        if (fileList != null) {
            for (File f : fileList) {
                files.add(f.getUrl());
            }
        }
    }
}
