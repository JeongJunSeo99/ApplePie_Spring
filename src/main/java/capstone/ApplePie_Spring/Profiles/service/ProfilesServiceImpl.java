package capstone.ApplePie_Spring.Profiles.service;

import capstone.ApplePie_Spring.Profiles.domain.Lesson;
import capstone.ApplePie_Spring.Profiles.domain.Outsourcing;
import capstone.ApplePie_Spring.Profiles.domain.Project;
import capstone.ApplePie_Spring.Profiles.dto.LessonDto;
import capstone.ApplePie_Spring.Profiles.dto.OutsourcingDto;
import capstone.ApplePie_Spring.Profiles.dto.ProfilesFindDto;
import capstone.ApplePie_Spring.Profiles.dto.ProjectDto;
import capstone.ApplePie_Spring.Profiles.repository.LessonRepository;
import capstone.ApplePie_Spring.Profiles.repository.OutsourcingRepository;
import capstone.ApplePie_Spring.Profiles.repository.ProjectRepository;
import capstone.ApplePie_Spring.Profiles.response.ResponseNoProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseOneProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseProfilesList;
import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.repository.ProfileRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfileRepository profileRepository;
    private final LessonRepository lessonRepository;
    private final OutsourcingRepository outsourcingRepository;
    private final ProjectRepository projectRepository;

    private static final Integer STATUS = 1;
    private static final Boolean OPEN = Boolean.TRUE;


    private Profile validateProfile(Long profileId) {
        Optional<Profile> findProfile = profileRepository.findByIdAndStatus(profileId, STATUS);
        return findProfile.orElse(null);
    }

    @Override
    public Object findLesson(ProfilesFindDto profilesFindDto)  {
        List<Lesson> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());
        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = lessonRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = lessonRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, Collections.singletonList(profiles));
    }

    @Override
    public Object findOutsourcing(ProfilesFindDto profilesFindDto)  {
        List<Outsourcing> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());
        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = outsourcingRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = outsourcingRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, Collections.singletonList(profiles));
    }

    @Override
    public Object findProject(ProfilesFindDto profilesFindDto)  {
        List<Project> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());
        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = projectRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = projectRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, Collections.singletonList(profiles));
    }

    @Override
    public Object findUserProfiles(Long profileId) {
        Profile profile = validateProfile(profileId);
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(profileId, STATUS);
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(profileId, STATUS);
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(profileId, STATUS);

        Lesson lesson = null;
        Project project = null;
        Outsourcing outsourcing = null;

        if (findLesson.isPresent()) {
            lesson = findLesson.get();
        }
        if (findProject.isPresent()) {
            project = findProject.get();
        }
        if (findOutsourcing.isPresent()) {
            outsourcing = findOutsourcing.get();
        }
        return new ResponseOneProfiles(ExceptionCode.PROFILES_FIND_OK, lesson, project, outsourcing);
    }

    @Override
    public Object saveLesson(Long profileId, LessonDto lessonDto) {
        Profile profile = validateProfile(profileId);
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findLesson.isPresent()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Lesson lesson = Lesson.builder()
                .subject(lessonDto.getSubject())
                .lessonSelf(lessonDto.getLessonSelf())
                .open(lessonDto.isOpen())
                .profile(profile)
                .build();
        lessonRepository.save(lesson);
        return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, lesson);
    }

    @Override
    public Object updateLesson(Long profileId, LessonDto lessonDto) {
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findLesson.isPresent()) {
            Lesson lesson = findLesson.get();
            lesson.update(lessonDto);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, lesson);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
    }

    @Override
    public Object updateOpenLesson(Long pid, boolean open) {
        Profile profile = validateProfile(pid);
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(pid, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findLesson.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Lesson lesson = findLesson.get();
        lesson.setOpen(open);
        if (lesson.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE__OK);

    }

    @Override
    public Object saveProject(Long profileId, ProjectDto projectDto) {
        Profile profile = validateProfile(profileId);
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findProject.isPresent()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Project project = Project.builder()
                .part(projectDto.getPart())
                .projectSelf(projectDto.getProjectSelf())
                .open(projectDto.isOpen())
                .profile(profile)
                .build();
        projectRepository.save(project);
        return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, project);
    }

    @Override
    public Object updateProject(Long profileId, ProjectDto projectDto) {
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findProject.isPresent()) {
            Project project = findProject.get();
            project.update(projectDto);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, project);
        } else {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
        }
    }

    @Override
    public Object updateOpenProject(Long pid, boolean open) {
        Profile profile = validateProfile(pid);
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(pid, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findProject.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Project project = findProject.get();
        project.setOpen(open);
        if (project.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE__OK);
    }

    @Override
    public Object saveOutsourcing(Long profileId, OutsourcingDto outsourcingDto) {
        Profile profile = validateProfile(profileId);
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findOutsourcing.isPresent()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Outsourcing outsourcing = Outsourcing.builder()
                .career(outsourcingDto.getCareer())
                .outsourcingSelf(outsourcingDto.getOutsourcingSelf())
                .open(outsourcingDto.isOpen())
                .profile(profile)
                .build();
        outsourcingRepository.save(outsourcing);
        return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, outsourcing);
    }

    @Override
    public Object updateOutsourcing(Long profileId, OutsourcingDto outsourcingDto) {
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findOutsourcing.isPresent()) {
            Outsourcing outsourcing = findOutsourcing.get();
            outsourcing.update(outsourcingDto);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, outsourcing);
        } else {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
        }
    }

    @Override
    public Object updateOpenOutsourcing(Long pid, boolean open) {
        Profile profile = validateProfile(pid);
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(pid, STATUS);
        if (profile == null) {
            return new ResponseNoProfiles(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        else if (findOutsourcing.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Outsourcing outsourcing = findOutsourcing.get();
        outsourcing.setOpen(open);
        if (outsourcing.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE__OK);
    }
}
