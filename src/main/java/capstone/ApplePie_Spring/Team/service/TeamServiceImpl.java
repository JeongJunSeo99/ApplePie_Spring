package capstone.ApplePie_Spring.Team.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.repository.BoardRepository;
import capstone.ApplePie_Spring.Board.resposne.ResponseNoBoard;
import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.Team.dto.TeamDto;
import capstone.ApplePie_Spring.Team.repository.VolunteerRepository;
import capstone.ApplePie_Spring.Team.repository.TeamRepository;
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
public class TeamServiceImpl implements TeamService {

    private static final Integer STATUS = 1;

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;
    private final VolunteerRepository volunteerRepository;

    public Object save(TeamDto teamDto) {
        Optional<Board> findBoard = boardRepository.findByIdAndStatus(teamDto.getBoardId(), STATUS);
        if (findBoard.isEmpty()) {
            return new ResponseNoBoard(ExceptionCode.BOARD_FIND_NOT);
        }

        Board board = findBoard.get();
        String email = findBoard.get().getUser().getEmail();
        User user = userRepository.findByEmailAndStatus(email, STATUS).get();

        Team team = Team.builder()
                .board(board)
                .teamDto(teamDto)
                .build();
        teamRepository.save(team);

        Volunteer volunteer = Volunteer.builder()
                .team(team)
                .user(user)
                .part(teamDto.getPart())
                .build();
        volunteerRepository.save(volunteer);

        return new ResponseTeam(ExceptionCode.TEAM_CREATED_OK);
    }
}
