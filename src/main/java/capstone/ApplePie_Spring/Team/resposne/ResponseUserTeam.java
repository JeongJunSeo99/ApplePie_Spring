package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.Board.dto.FindBoardListDto;
import capstone.ApplePie_Spring.Team.domain.Team;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserTeam extends ResponseType {

    private List<FindBoardListDto> boards;
    private List<Team> complete;
    private List<Team> incomplete;
    private List<Team> apply;

    public ResponseUserTeam(ExceptionCode exceptionCode, List<FindBoardListDto> board,
                            List<Team> complete, List<Team> incomplete, List<Team> apply) {
        super(exceptionCode);
        this.boards = board;
        this.complete = complete;
        this.incomplete = incomplete;
        this.apply = apply;
    }
}
