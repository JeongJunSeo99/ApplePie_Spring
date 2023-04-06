package capstone.ApplePie_Spring.Board.dto;

import lombok.Getter;

@Getter
public class BoardSaveDto {
    private String email;

    private String title;
    private String content;
    private int view_count;
    private int category;
}
