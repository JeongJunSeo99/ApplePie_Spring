package capstone.ApplePie_Spring.Profiles.domain;

import capstone.ApplePie_Spring.Profiles.dto.LessonDto;
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
@Entity(name = "LESSON")
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(name = "lesson_self", nullable = false)
    private String lessonSelf;


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

    public void setOpen(boolean open) {
        this.open = open;
    }


    @Builder
    public Lesson(String subject, String lessonSelf, boolean open, Profile profile) {
        this.subject = subject;
        this.lessonSelf = lessonSelf;
        this.open = open;
        this.profile = profile;
    }

    public void update(LessonDto lessonDto) {
        this.subject = lessonDto.getSubject();
        this.lessonSelf = lessonDto.getLessonSelf();
        this.open = lessonDto.isOpen();
    }

}