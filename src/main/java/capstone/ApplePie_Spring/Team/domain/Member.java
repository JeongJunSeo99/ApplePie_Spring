package capstone.ApplePie_Spring.Team.domain;

import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "MEMBER")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 연관 관계 매핑

    @ManyToOne
    private Team team;

    @ManyToOne
    private Volunteer volunteer;


    public void delete() {
        super.delete();
    }

    @Builder
    public Member(Team team, Volunteer volunteer) {
        this.team = team;
        this.volunteer = volunteer;
    }
}
