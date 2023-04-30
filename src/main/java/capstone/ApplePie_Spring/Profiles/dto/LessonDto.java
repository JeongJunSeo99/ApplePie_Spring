package capstone.ApplePie_Spring.Profiles.dto;

import capstone.ApplePie_Spring.Profiles.domain.Lesson;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonDto {
    private String subject;
    private String lessonSelf;
    private boolean open;

    @Builder
    public LessonDto(String subject, String lessonSelf, boolean open) {
        this.subject = subject;
        this.lessonSelf = lessonSelf;
        this.open = open;
    }

    public Lesson toLesson() {
        return Lesson.builder()
                .subject(subject)
                .lessonSelf(lessonSelf)
                .open(open)
                .build();
    }
}
