package capstone.ApplePie_Spring.Board.dto;

import lombok.Getter;

@Getter
public class BoardUpdateDto {
    private String email;

    private String title;
    private String content;
    private int category;
}
