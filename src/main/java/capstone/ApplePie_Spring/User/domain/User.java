package capstone.ApplePie_Spring.User.domain;

import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.domain.Board.Board;
import capstone.ApplePie_Spring.domain.Chat.ChatJoin;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    private char gender;
    private int age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(nullable = false)
    private boolean corp;

    // 연관 관계 매핑
    @OneToMany(mappedBy = "user")
    private List<ChatJoin> joins = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Board> board = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @Builder
    public User(String email, String name, String nickname, String password,
                char gender, int age, LocalDate birth, boolean corp) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.birth = birth;
        this.corp = corp;
    }

    // 연관관계 메소드
    public void delete() {
        if (profile != null) {
            profile.delete();
        }
        super.delete();
    }
}
