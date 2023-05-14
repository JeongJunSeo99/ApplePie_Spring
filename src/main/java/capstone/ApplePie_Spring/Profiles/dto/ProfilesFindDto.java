package capstone.ApplePie_Spring.Profiles.dto;

import capstone.ApplePie_Spring.Board.domain.Board;
import lombok.Getter;
import javax.annotation.Nullable;

@Getter
public class ProfilesFindDto {
    private Board.Category category;
    private int size;
    @Nullable
    private Long id; // 전달된 마지막 profiles id
}
