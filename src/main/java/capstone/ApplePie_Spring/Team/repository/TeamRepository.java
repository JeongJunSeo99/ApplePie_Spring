package capstone.ApplePie_Spring.Team.repository;

import capstone.ApplePie_Spring.Team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team save(Team team);
    Optional<Team> findByBoardIdAndStatus(Long boardId, int status);
}
