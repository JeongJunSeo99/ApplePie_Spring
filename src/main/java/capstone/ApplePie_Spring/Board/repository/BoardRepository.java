package capstone.ApplePie_Spring.Board.repository;

import capstone.ApplePie_Spring.Board.domain.Board;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board save(Board board);
    Optional<Board> findByIdAndStatus(Long id, int status);
    List<Board> findAllByCategoryAndStatusOrderByIdDesc(Board.Category category, int status, Pageable pageable);
    List<Board> findAllByCategoryAndStatusAndTitleContainingOrderByIdDesc(Board.Category category, String title, int status, Pageable pageable);
    List<Board> findAllByIdLessThanAndCategoryAndStatusOrderByIdDesc(long id, Board.Category category, int status, Pageable pageable);
    List<Board> findAllByIdLessThanAndCategoryAndStatusAndTitleContainingOrderByIdDesc(long id, Board.Category category, int status, String title, Pageable pageable);
}
