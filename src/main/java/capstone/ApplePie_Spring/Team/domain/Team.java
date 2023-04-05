package capstone.ApplePie_Spring.Team.domain;

import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.Board.domain.Board;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "TEAM")
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_content", nullable = false)
    private String teamContent;

    @Column(name = "team_state", nullable = false)
    private String teamState;

    @Column(name = "member_count", nullable = false)
    private int memberCount;

    // 연관 관계 매핑

    @OneToOne(mappedBy = "team")
    private Board board;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Volunteer> volunteers = new ArrayList<>();
}
