package capstone.ApplePie_Spring.Team.dto;

import capstone.ApplePie_Spring.Team.domain.Volunteer;
import lombok.Getter;


@Getter
public class VolunteerStatusDto {
    private Long volunteerId;
    private String nickname;
    private Volunteer.Role role;
    private Volunteer.VolunteerStatus volunteerStatus;
    private String details;

    public VolunteerStatusDto(Long id, Volunteer.Role role, Volunteer.VolunteerStatus status,
                              String details, String nickname) {
        this.volunteerId = id;
        this.nickname = nickname;
        this.role = role;
        this.volunteerStatus = status;
        this.details = details;
    }
}
