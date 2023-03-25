package capstone.ApplePie_Spring.domain.Board;

import capstone.ApplePie_Spring.config.BaseEntity;
import capstone.ApplePie_Spring.domain.Board.Board;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "FILE")
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    private String name;
    private String size;
    private String extension;

    // 연관 관계 매핑

    @ManyToOne
    private Board board;
}