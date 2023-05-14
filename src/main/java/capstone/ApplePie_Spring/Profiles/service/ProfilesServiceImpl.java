package capstone.ApplePie_Spring.Profiles.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Profiles.domain.Lesson;
import capstone.ApplePie_Spring.Profiles.domain.Outsourcing;
import capstone.ApplePie_Spring.Profiles.domain.Project;
import capstone.ApplePie_Spring.Profiles.dto.*;
import capstone.ApplePie_Spring.Profiles.repository.LessonRepository;
import capstone.ApplePie_Spring.Profiles.repository.OutsourcingRepository;
import capstone.ApplePie_Spring.Profiles.repository.ProjectRepository;
import capstone.ApplePie_Spring.Profiles.response.ResponseNoProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseOneProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseProfiles;
import capstone.ApplePie_Spring.Profiles.response.ResponseProfilesList;
import capstone.ApplePie_Spring.Team.resposne.ResponseTeam;
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


    @Override
    public Object findProfiles(ProfilesFindDto profilesFindDto) {

        if (profilesFindDto.getCategory().equals(Board.Category.OUTSOURCING)) {
            return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, findOutsourcing(profilesFindDto));
        }
        else if (profilesFindDto.getCategory().equals(Board.Category.LESSON)) {
            return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, findLesson(profilesFindDto));
        }
        else {
            return new ResponseProfilesList(ExceptionCode.PROFILES_FIND_OK, findProject(profilesFindDto));
        }


    }

    public List<Object> findProject(ProfilesFindDto profilesFindDto) {
        List<Project> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());

        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = projectRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = projectRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return Collections.singletonList(profiles);
    }

    public List<Object> findLesson(ProfilesFindDto profilesFindDto) {
        List<Lesson> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());

        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = lessonRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = lessonRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return Collections.singletonList(profiles);
    }

    public List<Object> findOutsourcing(ProfilesFindDto profilesFindDto) {
        List<Outsourcing> profiles;
        PageRequest pageRequest = PageRequest.of(0, profilesFindDto.getSize());

        if (profilesFindDto.getId() == null) { // 처음 조회
            profiles = outsourcingRepository.findAllByStatusAndOpenOrderByIdDesc(STATUS, OPEN, pageRequest);
        }
        else {
            profiles = outsourcingRepository.findAllByIdLessThanAndStatusAndOpenOrderByIdDesc(profilesFindDto.getId(), STATUS, OPEN, pageRequest);
        }
        return Collections.singletonList(profiles);
    }

    @Override
    public Object saveProfiles(Long pid, ProfilesDto profilesDto) {
        Optional<Profile> findProfile = profileRepository.findByIdAndStatus(pid, STATUS);
        if (findProfile.isEmpty()) {
            return new ResponseTeam(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        Profile profile = findProfile.get();

        if (profilesDto.getCategory().equals(Board.Category.OUTSOURCING)) {
            Outsourcing outsourcing = profilesDto.toOutsourcing(profile);
            outsourcingRepository.save(outsourcing);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, outsourcing);
        }
        else if (profilesDto.getCategory().equals(Board.Category.LESSON)) {
            Lesson lesson = profilesDto.toLesson(profile);
            lessonRepository.save(lesson);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, lesson);
        }
        else {
            Project project = profilesDto.toProject(profile);
            projectRepository.save(project);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, project);
        }
    }

    @Override
    public Object updateProfiles(Long pid, ProfilesDto profilesDto) {
        Optional<Profile> findProfile = profileRepository.findByIdAndStatus(pid, STATUS);
        if (findProfile.isEmpty()) {
            return new ResponseTeam(ExceptionCode.USER_PROFILE_FIND_NOT);
        }
        Profile profile = findProfile.get();

        if (profilesDto.getCategory().equals(Board.Category.OUTSOURCING)) {
            return updateOutsourcing(pid, profilesDto.toOutsourcing(profile));
        }
        else if (profilesDto.getCategory().equals(Board.Category.LESSON)) {
            return updateLesson(pid, profilesDto.toLesson(profile));
        }
        else {
            return updateProject(pid, profilesDto.toProject(profile));
        }
    }

    @Override
    public Object openProfiles(Long pid, OpenDto openDto) {
        Optional<Profile> findProfile = profileRepository.findByIdAndStatus(pid, STATUS);
        if (findProfile.isEmpty()) {
            return new ResponseTeam(ExceptionCode.USER_PROFILE_FIND_NOT);
        }

        if (openDto.getCategory().equals(Board.Category.OUTSOURCING)) {
            return updateOpenOutsourcing(pid, openDto.isOpen());
        }
        else if (openDto.getCategory().equals(Board.Category.LESSON)) {
            return updateOpenLesson(pid, openDto.isOpen());
        }
        else {
            return updateOpenProject(pid, openDto.isOpen());
        }
    }

    @Override
    public Object findUserProfiles(Long profileId) {
        Optional<Profile> findProfile = profileRepository.findByIdAndStatus(profileId, STATUS);
        if (findProfile.isEmpty()) {
            return new ResponseTeam(ExceptionCode.USER_PROFILE_FIND_NOT);
        }

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

    public Object updateLesson(Long profileId, Lesson lesson) {
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findLesson.isPresent()) {
            findLesson.get().update(lesson);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, lesson);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
    }

    public Object updateProject(Long profileId, Project project) {
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findProject.isPresent()) {
            findProject.get().update(project);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, project);
        } else {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
        }
    }

    public Object updateOutsourcing(Long profileId, Outsourcing outsourcing) {
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(profileId, STATUS);
        if (findOutsourcing.isPresent()) {
            findOutsourcing.get().update(outsourcing);
            return new ResponseProfiles(ExceptionCode.PROFILES_CREATED_OK, outsourcing);
        } else {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_FIND_NOT);
        }
    }

    public Object updateOpenLesson(Long pid, boolean open) {
        Optional<Lesson> findLesson = lessonRepository.findByProfileIdAndStatus(pid, STATUS);
        if (findLesson.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Lesson lesson = findLesson.get();
        lesson.setOpen(open);
        if (lesson.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE_OK);
    }

    public Object updateOpenProject(Long pid, boolean open) {
        Optional<Project> findProject = projectRepository.findByProfileIdAndStatus(pid, STATUS);
        if (findProject.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Project project = findProject.get();
        project.setOpen(open);
        if (project.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE_OK);
    }

    public Object updateOpenOutsourcing(Long pid, boolean open) {
        Optional<Outsourcing> findOutsourcing = outsourcingRepository.findByProfileIdAndStatus(pid, STATUS);
        if (findOutsourcing.isEmpty()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_CREATED_ERROR);
        }

        Outsourcing outsourcing = findOutsourcing.get();
        outsourcing.setOpen(open);
        if (outsourcing.isOpen()) {
            return new ResponseNoProfiles(ExceptionCode.PROFILES_OPEN_OK);
        }
        return new ResponseNoProfiles(ExceptionCode.PROFILES_CLOSE_OK);
    }
}
