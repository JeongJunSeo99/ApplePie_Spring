package capstone.ApplePie_Spring.domain.Profile;

import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "PROJECT")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(nullable = false)
    private String part;

    @Column(name = "project_self", nullable = false)
    private String projectSelf;

    @Column(nullable = false)
    private Boolean open;


    // 연관 관계 매핑
    @OneToOne
    private Profile profile;
}