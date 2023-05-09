package capstone.ApplePie_Spring.Team.service;

import capstone.ApplePie_Spring.Team.dto.TeamDto;
import capstone.ApplePie_Spring.Team.dto.TeamUpdateDto;

public interface TeamService {
    Object save(TeamDto teamDto);
    Object complete(TeamUpdateDto teamUpdateDto);
    Object update(TeamDto teamDto);
    Object cancel(TeamUpdateDto teamUpdateDto);
    Object delete(TeamUpdateDto teamUpdateDto);

    Object findTeam(Long userId);
}
