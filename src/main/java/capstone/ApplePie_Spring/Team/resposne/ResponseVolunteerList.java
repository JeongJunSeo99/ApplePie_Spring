package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.Team.domain.Volunteer;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseVolunteerList extends ResponseType {

    private List<Integer> totalCount;
    private List<Integer> count;
    private List<Volunteer> volunteerList;
    public ResponseVolunteerList(ExceptionCode exceptionCode, List<Volunteer> volunteers,
                                 List<Integer> count, List<Integer> totalCount) {
        super(exceptionCode);
        this.volunteerList = volunteers;
        this.count = count;
        this.totalCount = totalCount;
    }
}
