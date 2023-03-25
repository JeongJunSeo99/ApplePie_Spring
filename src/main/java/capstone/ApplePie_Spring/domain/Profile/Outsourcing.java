package capstone.ApplePie_Spring.domain.Profile;

import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "OUTSOURCING")
public class Outsourcing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outsourcing_id")
    private Long outsourcingId;

    @Column(nullable = false)
    private String career;

    @Column(name = "outsourcing_self", nullable = false)
    private String outsourcingSelf;

    @Column(nullable = false)
    private Boolean open;

    // 연관 관계 매핑

    @OneToOne
    private Profile profile;
}