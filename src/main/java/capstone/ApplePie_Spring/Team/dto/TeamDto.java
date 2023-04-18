package capstone.ApplePie_Spring.Team.dto;

import lombok.Getter;

@Getter
public class TeamDto {
    private Long boardId;

    private String teamName;
    private String teamContent;
    private int memberCount;

    private String part; // 팀장 역할
}