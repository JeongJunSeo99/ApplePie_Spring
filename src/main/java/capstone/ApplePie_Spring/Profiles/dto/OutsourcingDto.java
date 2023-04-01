package capstone.ApplePie_Spring.Profiles.dto;

import capstone.ApplePie_Spring.Profiles.domain.Outsourcing;
import capstone.ApplePie_Spring.config.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OutsourcingDto extends BaseEntity {
    private String career;
    private String outsourcingSelf;
    private boolean open;

    @Builder
    public OutsourcingDto(String career, String outsourcingSelf, boolean open) {
        this.career = career;
        this.outsourcingSelf = outsourcingSelf;
        this.open = open;
    }

    public Outsourcing toOutsourcing() {
        return Outsourcing.builder()
                .career(career)
                .outsourcingSelf(outsourcingSelf)
                .open(open)
                .build();
    }

    public void delete() {
        super.delete();
    }
}
