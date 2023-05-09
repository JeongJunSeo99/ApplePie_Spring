package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseVolunteerList extends ResponseType {

    private List<Volunteer> volunteerList;
    public ResponseVolunteerList(ExceptionCode exceptionCode, List<Volunteer> volunteers) {
        super(exceptionCode);
        this.volunteerList = volunteers;
    }
}
