package capstone.ApplePie_Spring.User.domain;

import capstone.ApplePie_Spring.Profiles.domain.Lesson;
import capstone.ApplePie_Spring.Profiles.domain.Outsourcing;
import capstone.ApplePie_Spring.Profiles.domain.Project;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;
import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "PROFILE")
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String area;
    private String college;
    private String grader;
    private float grade;
    private String github;

    @Column(name = "dev_framework")
    private String devFramework;

    @Column(name = "dev_language")
    private String devLanguage;

    // 연관 관계 매핑
    @OneToOne // (fetch = FetchType.LAZY)는 일대일 매핑에서 오류
    private User user;

    @OneToOne(mappedBy = "profile")
    private Project project;

    @OneToOne(mappedBy = "profile")
    private Lesson lesson;

    @OneToOne(mappedBy = "profile")
    private Outsourcing outsourcing;

    // 연관관계 메소드
    public void delete() {
        if (lesson != null) {
            lesson.delete();
        }
        if (outsourcing != null) {
            outsourcing.delete();
        }
        if (project != null) {
            project.delete();
        }
        super.delete();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(ProfileDto profileDto) {
        this.area = profileDto.getArea();
        this.college = profileDto.getCollege();
        this.grade = profileDto.getGrade();
        this.grader = profileDto.getGrader();
        this.github = profileDto.getGithub();
        this.devFramework = profileDto.getDevFramework();
        this.devLanguage = profileDto.getDevLanguage();
    }

    @Builder
    public Profile(SignupDto signupDto, User user) {
        this.user = user;
        this.college = signupDto.getCollege();
        this.grade = signupDto.getGrade();
        this.area = signupDto.getArea();
        this.grader = signupDto.getArea();
        this.github = signupDto.getGithub();
        this.devFramework = signupDto.getDevFramework();
        this.devLanguage = signupDto.getDevLanguage();
    }

    @Builder(builderMethodName = "updateBuilder")
    public Profile(ProfileDto profileDto, User user) {
        this.college = profileDto.getCollege();
        this.grade = profileDto.getGrade();
        this.area = profileDto.getArea();
        this.grader = profileDto.getArea();
        this.github = profileDto.getGithub();
        this.devFramework = profileDto.getDevFramework();
        this.devLanguage = profileDto.getDevLanguage();
    }
}
