package capstone.ApplePie_Spring.Profiles.response;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseProfilesList extends ResponseType {
    private List<Object> data;

    public ResponseProfilesList(ExceptionCode exceptionCode, List<Object> data) {
        super(exceptionCode);
        this.data = data;
    }
}
