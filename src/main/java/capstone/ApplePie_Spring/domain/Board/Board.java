package capstone.ApplePie_Spring.domain.Board;

import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.domain.Team.Team;
import capstone.ApplePie_Spring.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "BOARD")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int view_count;

    // 연관 관계 매핑

    @ManyToOne
    private User user;

    @OneToOne
    private Team team;

    @OneToOne
    private Category category; // 단방향 매핑

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();
}
