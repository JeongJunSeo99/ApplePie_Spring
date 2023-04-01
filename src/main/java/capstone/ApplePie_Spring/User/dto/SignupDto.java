package capstone.ApplePie_Spring.User.dto;

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
    private int age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Builder
    public SignupDto(String email, String name,
                     String nickname, String password,
                     boolean corp, char gender, int age, LocalDate birth) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.birth = birth;
        this.corp = corp;
    }

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .gender(gender)
                .age(age)
                .birth(birth)
                .corp(corp)
                .build();
    }
}
