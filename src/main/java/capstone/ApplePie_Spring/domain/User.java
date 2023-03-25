package capstone.ApplePie_Spring.domain;

import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.domain.Board.Board;
import capstone.ApplePie_Spring.domain.Chat.ChatJoin;
import capstone.ApplePie_Spring.domain.Profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private char nickname;

    private char gender;
    private int age;
    private Date birth;
    private boolean corp;

    // 연관 관계 매핑

    @OneToMany(mappedBy = "user")
    private List<ChatJoin> joins = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    List<Board> board = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @Builder
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
