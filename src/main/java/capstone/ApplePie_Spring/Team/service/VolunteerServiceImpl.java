package capstone.ApplePie_Spring.Team.service;

import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.Team.dto.VolunteerDto;
import capstone.ApplePie_Spring.Team.repository.TeamRepository;
import capstone.ApplePie_Spring.Team.repository.VolunteerRepository;
import capstone.ApplePie_Spring.Team.resposne.ResponseTeam;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.repository.UserRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private static final Integer STATUS = 1;

    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public Object save(VolunteerDto volunteerDto) {
        Optional<User> findUser = userRepository.findByEmailAndStatus(volunteerDto.getEmail(), STATUS);
        Optional<Team> findTeam = teamRepository.findByBoardIdAndStatus(volunteerDto.getBoardId(), STATUS);

        if (findTeam.isEmpty() || findUser.isEmpty()) {
            return new ResponseTeam(ExceptionCode.WRONG_APPROACH);
        }

        Volunteer volunteer = Volunteer.builder()
                .user(findUser.get())
                .team(findTeam.get())
                .part(volunteerDto.getPart())
                .build();
        volunteerRepository.save(volunteer);
        return new ResponseTeam(ExceptionCode.VOLUNTEER_OK);
    }
}
