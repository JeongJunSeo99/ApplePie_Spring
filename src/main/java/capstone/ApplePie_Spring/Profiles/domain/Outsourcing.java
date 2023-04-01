package capstone.ApplePie_Spring.Profiles.domain;

import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "OUTSOURCING")
public class Outsourcing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String career;

    @Column(name = "outsourcing_self", nullable = false)
    private String outsourcingSelf;

    @Column(nullable = false)
    private boolean open;

    // 연관 관계 매핑
    @OneToOne
    private Profile profile;

    // 연관관계 메소드
    public void delete() {
        super.delete();
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Builder
    public Outsourcing(String career, String outsourcingSelf, boolean open) {
        this.career = career;
        this.outsourcingSelf = outsourcingSelf;
        this.open = open;
    }
}