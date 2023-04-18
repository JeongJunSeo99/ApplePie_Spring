package capstone.ApplePie_Spring.Team.controller;

import capstone.ApplePie_Spring.Team.dto.MemberDto;
import capstone.ApplePie_Spring.Team.dto.TeamDto;
import capstone.ApplePie_Spring.Team.dto.VolunteerDto;
import capstone.ApplePie_Spring.Team.service.MemberService;
import capstone.ApplePie_Spring.Team.service.TeamService;
import capstone.ApplePie_Spring.Team.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/teams")
@RestController
@RequiredArgsConstructor
public class TeamController {
    
    private final TeamService teamService;
    private final VolunteerService volunteerService;
    private final MemberService memberService;

    // 팀 생성
    @PostMapping
    public ResponseEntity<Object> createTeam(@RequestBody TeamDto teamDto) {
        return new ResponseEntity<>(teamService.save(teamDto), HttpStatus.OK);
    }

    // 팀 지원
    @PostMapping("/volunteer")
    public ResponseEntity<Object> createVolunteer(@RequestBody VolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.save(volunteerDto), HttpStatus.OK);
    }

    // 팀원 처리
    @PostMapping("/member")
    public ResponseEntity<Object> updateVolunteerToMember(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.OK);
    }
}
