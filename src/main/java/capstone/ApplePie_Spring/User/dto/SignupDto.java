package capstone.ApplePie_Spring.User.dto;

import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignupDto {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private boolean corp;

    private char gender;
    //private int age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String area;
    private String college;
    private String grader;
    private float grade;
    private String github;
    private String devFramework;
    private String devLanguage;

    @Builder
    public SignupDto(String email, String name, String nickname, String password,
                     boolean corp, char gender, LocalDate birth, //int age,
                     String college, float grade, String area, String grader, String github,
                     String devFramework, String devLanguage) {

        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        //this.age = age;
        this.birth = birth;
        this.corp = corp;

        this.area = area;
        this.college = college;
        this.grade = grade;
        this.grader = grader;
        this.github = github;
        this.devFramework = devFramework;
        this.devLanguage = devLanguage;
    }

    public User toUser() {
        return User.builder()
                .signupDto(this)
                .build();
    }

    public Profile toProfile(User user) {
        return Profile.builder()
                .signupDto(this)
                .user(user)
                .build();
    }
}
