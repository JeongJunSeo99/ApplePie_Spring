package capstone.ApplePie_Spring.Team.repository;

import capstone.ApplePie_Spring.Team.domain.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Volunteer save(Volunteer volunteer);
    Optional<Volunteer> findByIdAndStatus(Long id, int status);
    List<Volunteer> findByUserIdAndStatus(Long userId, int status);
    List<Volunteer> findByTeamIdAndStatus(Long teamId, int status);
}
