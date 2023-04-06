package capstone.ApplePie_Spring.Board.repository;

import capstone.ApplePie_Spring.Board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board save(Board board);
    //Optional<Board> findByIdAndStatus(Long id, int status);
}
