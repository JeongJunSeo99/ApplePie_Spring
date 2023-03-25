package capstone.ApplePie_Spring.domain.Profile;

import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "LESSON")
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(nullable = false)
    private String subject;

    @Column(name = "lesson_self", nullable = false)
    private String lessonSelf;


    @Column(nullable = false)
    private Boolean open;

    // 연관 관계 매핑

    @OneToOne
    private Profile profile;
}