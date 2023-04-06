package capstone.ApplePie_Spring.Board.domain;

import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.User.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public enum Category {
        OUTSOURCING(1,"외주"),
        LESSON(2,"과외"),
        PROJECT(3, "공모전");

        private int id;
        private String name;

        Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static Category getValue(int id) {
            return Arrays.stream(values())
                    .filter(value -> value.id == id)
                    .findAny()
                    .orElse(null);
        }
    }

    private Category category; // 단방향 매핑

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<File> files = new ArrayList<>();

    @Builder
    public Board(BoardSaveDto boardSaveDto, User user) {
        this.title = boardSaveDto.getTitle();
        this.content = boardSaveDto.getContent();
        this.view_count = 0;
        this.category = Category.getValue(boardSaveDto.getCategory());

        this.user = user;
    }
}
