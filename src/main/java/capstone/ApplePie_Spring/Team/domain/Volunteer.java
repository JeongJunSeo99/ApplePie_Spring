package capstone.ApplePie_Spring.Team.domain;

import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "VOLUNTEER")
public class Volunteer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String part;

    // 연관 관계 매핑

    @ManyToOne
    private User user;

    @ManyToOne
    private Team team;

    @Builder
    public Volunteer(User user, Team team, String part) {
        this.user = user;
        this.team = team;
        this.part = part;
    }

    public void delete() {
        super.delete();
    }
}
