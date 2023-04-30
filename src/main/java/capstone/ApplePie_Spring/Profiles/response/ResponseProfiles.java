package capstone.ApplePie_Spring.Profiles.response;

import capstone.ApplePie_Spring.Profiles.domain.Lesson;
import capstone.ApplePie_Spring.Profiles.domain.Outsourcing;
import capstone.ApplePie_Spring.Profiles.domain.Project;
import capstone.ApplePie_Spring.Profiles.dto.LessonDto;
import capstone.ApplePie_Spring.Profiles.dto.OutsourcingDto;
import capstone.ApplePie_Spring.Profiles.dto.ProjectDto;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
// @JsonInclude(JsonInclude.Include.NON_NULL) // null 정보  제거
public class ResponseProfiles extends ResponseType {
    private List<Object> data;
    private LessonDto lesson;
    private ProjectDto project;
    private OutsourcingDto outsourcing;

    public ResponseProfiles(ExceptionCode exceptionCode, Lesson lesson, Project project, Outsourcing outsourcing) {
        super(exceptionCode);
        toLessonDto(lesson);
        toProjectDto(project);
        toOutsourcingDto(outsourcing);
    }

    public ResponseProfiles(ExceptionCode exceptionCode, Lesson lesson) {
        super(exceptionCode);
        toLessonDto(lesson);
    }

    public ResponseProfiles(ExceptionCode exceptionCode, Project project) {
        super(exceptionCode);
        toProjectDto(project);
    }

    public ResponseProfiles(ExceptionCode exceptionCode, Outsourcing outsourcing) {
        super(exceptionCode);
        toOutsourcingDto(outsourcing);
    }

    public void toProjectDto(Project project) {
        if (project == null) {
            this.project = null;
        }
        else {
            this.project = ProjectDto.builder()
                    .part(project.getPart())
                    .projectSelf(project.getProjectSelf())
                    .open(project.isOpen())
                    .build();
        }
    }

    public void toLessonDto(Lesson lesson) {
        if (lesson == null) {
            this.lesson = null;
        }
        else {
            this.lesson = LessonDto.builder()
                    .subject(lesson.getSubject())
                    .lessonSelf(lesson.getLessonSelf())
                    .open(lesson.isOpen())
                    .build();
        }
    }

    public void toOutsourcingDto(Outsourcing outsourcing) {
        if (outsourcing == null) {
            this.outsourcing = null;
        }
        else {
            this.outsourcing = OutsourcingDto.builder()
                    .career(outsourcing.getCareer())
                    .outsourcingSelf(outsourcing.getOutsourcingSelf())
                    .open(outsourcing.isOpen())
                    .build();
        }
    }
}
