package capstone.ApplePie_Spring.domain.Profile;

import capstone.ApplePie_Spring.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "PROFILE")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    private String area;
    private char college;
    private String grader;
    private float grade;
    private String github;

    @Column(name = "dev_framework")
    private String devFramework;

    @Column(name = "dev_language")
    private String devLanguage;

    // 연관 관계 매핑

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "profile")
    private Project project;

    @OneToOne(mappedBy = "profile")
    private Lesson lesson;

    @OneToOne(mappedBy = "profile")
    private Outsourcing outsourcing;
}
