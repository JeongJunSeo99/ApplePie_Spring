package capstone.ApplePie_Spring.User.dto;

import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private String email;
    private String name;
    private String nickname;
    private boolean corp;
    private char gender;
    //private int age;
    private LocalDate birth;

    private String area;
    private String college;
    private String grader;
    private float grade;
    private String github;
    private String devFramework;
    private String devLanguage;

    @Builder
    public UserDto(User user, Profile profile) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.corp = user.isCorp();
        this.gender = user.getGender();
        //this.age = user.getAge();
        this.birth = user.getBirth();

        this.area = profile.getArea();
        this.college = profile.getCollege();
        this.grader = profile.getGrader();
        this.grade = profile.getGrade();
        this.github = profile.getGithub();
        this.devFramework = profile.getDevFramework();
        this.devLanguage = profile.getDevLanguage();
    }
}
