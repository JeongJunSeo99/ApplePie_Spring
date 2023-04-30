package capstone.ApplePie_Spring.Profiles.domain;

import capstone.ApplePie_Spring.Profiles.dto.ProjectDto;
import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "PROJECT")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String part;

    @Column(name = "project_self", nullable = false)
    private String projectSelf;

    @Column(nullable = false)
    private boolean open;

    // 연관 관계 매핑
    @JsonIgnore
    @OneToOne
    private Profile profile;

    // 연관관계 메소드
    public void delete() {
        super.delete();
    }

    @Builder
    public Project(String part, String projectSelf, boolean open, Profile profile) {
        this.part = part;
        this.projectSelf = projectSelf;
        this.open = open;
        this.profile = profile;
    }

    public void update(ProjectDto projectDto) {
        this.part = projectDto.getPart();
        this.projectSelf = projectDto.getProjectSelf();
        this.open = projectDto.isOpen();
    }
}