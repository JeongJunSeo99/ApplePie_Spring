package capstone.ApplePie_Spring.User.response;

import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.dto.UserDto;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseProfile extends ResponseType {

    private Long profileId;
    private UserDto data;

    public ResponseProfile(ExceptionCode exceptionCode, User user, Profile profile) {
        super(exceptionCode);
        this.profileId = profile.getId();
        this.data = UserDto.builder()
                .user(user)
                .profile(profile)
                .build();
    }
}

