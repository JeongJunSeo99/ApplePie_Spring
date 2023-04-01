package capstone.ApplePie_Spring.User.dto;

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
    private int age;
    private LocalDate birth;

    @Builder
    public UserDto(String email, String name, String nickname,
                   boolean corp, char gender, int age, LocalDate birth) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.birth = birth;
        this.corp = corp;
    }
}
