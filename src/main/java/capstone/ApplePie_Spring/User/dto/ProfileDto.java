package capstone.ApplePie_Spring.User.dto;

import capstone.ApplePie_Spring.User.domain.Profile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
    private String area;
    private String college;
    private String grader;
    private float grade;
    private String github;
    private String devFramework;
    private String devLanguage;

    @Builder
    public ProfileDto(String college, float grade, String area, String grader, String github,
                      String devFramework, String devLanguage) {
        this.area = area;
        this.college = college;
        this.grade = grade;
        this.grader = grader;
        this.github = github;
        this.devFramework = devFramework;
        this.devLanguage = devLanguage;
    }

    public Profile toProfile() {
        return Profile.builder()
                .area(area)
                .college(college)
                .github(github)
                .grade(grade)
                .grader(grader)
                .devFramework(devFramework)
                .devLanguage(devLanguage)
                .build();
    }
}
