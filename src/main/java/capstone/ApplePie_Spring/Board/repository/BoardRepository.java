package capstone.ApplePie_Spring.Board.repository;

import capstone.ApplePie_Spring.Board.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board save(Board board);
    Optional<Board> findByIdAndStatus(Long id, int status);
    boolean existsByIdAndStatus(Long id, int status);

    List<Board> findAllByUserIdAndStatusOrderByIdDesc(Long userId, int status);

    List<Board> findAllByCategoryAndStatusOrderByIdDesc(Board.Category category, int status, Pageable pageable);
    List<Board> findAllByCategoryAndStatusAndTitleContainingOrderByIdDesc(Board.Category category, String title, int status, Pageable pageable);
    List<Board> findAllByIdLessThanAndCategoryAndStatusOrderByIdDesc(long id, Board.Category category, int status, Pageable pageable);
    List<Board> findAllByIdLessThanAndCategoryAndStatusAndTitleContainingOrderByIdDesc(long id, Board.Category category, int status, String title, Pageable pageable);

    List<Board> findAllByCategoryAndStatus(Board.Category category, int status);

    @Query(value = "select * from Board b where b.category = ?1 and b.status = ?2 order by b.view_count desc limit 3" , nativeQuery = true)
    List<Board> ViewCount(Board.Category category, int status);

    List<Board> findAllByCategoryAndStatusOrderByViewCountDesc(Board.Category category, int status);
}
