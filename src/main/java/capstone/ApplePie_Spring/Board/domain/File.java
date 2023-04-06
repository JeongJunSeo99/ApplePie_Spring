package capstone.ApplePie_Spring.Board.domain;

import capstone.ApplePie_Spring.Board.dto.FileDto;
import capstone.ApplePie_Spring.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "FILE")
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private long size;
    private String extension;

    // 연관 관계 매핑

    @ManyToOne
    private Board board;

    @Builder
    public File(FileDto fileDto, Board board) {
        this.name = fileDto.getName();
        this.size = fileDto.getSize();
        this.extension = fileDto.getExtension();

        this.board = board;
        board.getFiles().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
