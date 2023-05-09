package capstone.ApplePie_Spring.Team.domain;

import capstone.ApplePie_Spring.Team.dto.TeamDto;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.Board.domain.Board;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Column(name = "count", nullable = false)
    private List<Integer> count;

    public enum TeamStatus {
        CANCEL(0, "모집 종료"),
        CREATE(1,"모집중"),
        COMPLETE(2,"모집 완료");

        private int id;
        private String name;

        TeamStatus(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static TeamStatus getValue(int id) {
            return Arrays.stream(values())
                    .filter(value -> value.id == id)
                    .findAny()
                    .orElse(null);
        }
    }

    @Column(name = "team_status", nullable = false)
    private TeamStatus teamStatus;


    // 연관 관계 매핑
    @JsonIgnore
    @OneToOne
    private User user;

    @JsonIgnore
    @OneToOne
    private Board board;

    @JsonBackReference
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "team")
    private List<Volunteer> volunteers = new ArrayList<>();

    @Builder
    public Team(TeamDto teamDto, Board board, User user) {
        this.user = user;
        this.teamName = teamDto.getTeamName();
        this.teamContent = teamDto.getTeamContent();
        this.count = teamDto.getCount();
        this.teamStatus = TeamStatus.CREATE;
        this.board = board;
    }

    public void updateTeam(TeamDto teamDto, List<Integer> count) {
        this.teamName = teamDto.getTeamName();
        this.teamContent = teamDto.getTeamContent();
        this.count = count;
    }

    public void addMember(Member member) {
        members.add(member);
        int index = member.getVolunteer().getRole().ordinal();
        count.set(index, count.get(index) - 1);
    }

    public void removeMember(Member member) {
        int index = member.getVolunteer().getRole().ordinal();
        count.set(index, count.get(index) + 1);
        members.remove(member);
    }

    public void addVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
    }

    public void removeVolunteer(Volunteer volunteer) {
        volunteers.remove(volunteer);
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public void delete() {
        for (Member member : members) {
            member.delete();
        }
        members.clear();
        for (Volunteer volunteer : volunteers) {
            volunteer.delete();
        }
        volunteers.clear();

        super.delete();
    }
}
