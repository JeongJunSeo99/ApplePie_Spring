package capstone.ApplePie_Spring.Profiles.dto;

import lombok.Getter;
import javax.annotation.Nullable;

@Getter
public class ProfilesFindDto {
    private int size;
    @Nullable
    private Long id; // 전달된 마지막 profiles id
}
