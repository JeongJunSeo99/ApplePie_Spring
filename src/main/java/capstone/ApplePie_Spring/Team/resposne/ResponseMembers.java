package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.Team.domain.Member;
import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMembers extends ResponseType {

    private List<Member> member;

    public ResponseMembers(ExceptionCode exceptionCode, List<Member> members) {
        super(exceptionCode);
        this.member = members;
    }
}
