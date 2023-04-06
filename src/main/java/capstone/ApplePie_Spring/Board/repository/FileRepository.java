package capstone.ApplePie_Spring.Board.repository;

import capstone.ApplePie_Spring.Board.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File save(File file);
    List<File> findByBoardIdAndStatus(Long boardId, int status);
}
