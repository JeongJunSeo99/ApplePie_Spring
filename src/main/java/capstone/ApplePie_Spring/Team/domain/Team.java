package capstone.ApplePie_Spring.Team.domain;

import capstone.ApplePie_Spring.Team.dto.TeamDto;
import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.Board.domain.Board;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    public enum TeamStatus {
        CREATE, CANCEL, COMPLETE
    }

    @Column(name = "team_status", nullable = false)
    private TeamStatus teamStatus;

    @Column(name = "member_count", nullable = false)
    private int memberCount;
    // 연관 관계 매핑

    @OneToOne(mappedBy = "team")
    private Board board;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Volunteer> volunteers = new ArrayList<>();

    @Builder
    public Team(TeamDto teamDto, Board board, TeamStatus teamStatus) {
        this.teamName = teamDto.getTeamName();
        this.teamContent = teamDto.getTeamContent();
        this.board = board;
        this.teamStatus = teamStatus;
        this.memberCount = teamDto.getMemberCount();
        this.teamStatus = TeamStatus.CREATE;
    }

    public void addMember(Member member) {
        members.add(member);
        memberCount -= 1;
    }

    /*public void delete() {
        for(Member member : members) {
            member.delete();
        }
        members.clear();
        this.delete();
    }*/
}
