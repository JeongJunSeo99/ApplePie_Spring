package capstone.ApplePie_Spring.User.Response;

import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.dto.UserDto;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseFindUser extends ResponseType {
    private UserDto user;
    private ProfileDto profile;

    public ResponseFindUser(ExceptionCode exceptionCode, User user, Profile profile) {
        super(exceptionCode);
        this.user = toUserDto(user);
        this.profile = toProfileDto(profile);
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .name(user.getName())
                .gender(user.getGender())
                .age(user.getAge())
                .birth(user.getBirth())
                .corp(user.isCorp())
                .build();
    }

    public ProfileDto toProfileDto(Profile profile) {
        if (profile == null) {
            return null;
        }
        return ProfileDto.builder()
                .area(profile.getArea())
                .college(profile.getCollege())
                .grader(profile.getGrader())
                .grade(profile.getGrade())
                .github(profile.getGithub())
                .devLanguage(profile.getDevLanguage())
                .devFramework(profile.getDevFramework())
                .build();
    }
}

