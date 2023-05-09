package capstone.ApplePie_Spring.Profiles.service;

import capstone.ApplePie_Spring.Profiles.dto.LessonDto;
import capstone.ApplePie_Spring.Profiles.dto.OutsourcingDto;
import capstone.ApplePie_Spring.Profiles.dto.ProfilesFindDto;
import capstone.ApplePie_Spring.Profiles.dto.ProjectDto;

public interface ProfilesService {

    Object findUserProfiles(Long profileId);

    Object saveLesson(Long profileId, LessonDto lessonDto);
    Object findLesson(ProfilesFindDto profilesFindDto);
    Object updateLesson(Long profileId, LessonDto lessonDto);
    Object updateOpenLesson(Long pid, boolean open);

    Object saveProject(Long profileId, ProjectDto projectDto);
    Object findProject(ProfilesFindDto profilesFindDto);
    Object updateProject(Long profileId, ProjectDto projectDto);
    Object updateOpenProject(Long pid, boolean open);

    Object saveOutsourcing(Long profileId, OutsourcingDto outsourcingDto);
    Object findOutsourcing(ProfilesFindDto profilesFindDto);
    Object updateOutsourcing(Long profileId, OutsourcingDto outsourcingDto);
    Object updateOpenOutsourcing(Long pid, boolean open);
}
