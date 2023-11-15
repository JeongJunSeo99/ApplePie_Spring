package capstone.ApplePie_Spring.Team.controller;


import capstone.ApplePie_Spring.Team.dto.*;
import capstone.ApplePie_Spring.Team.service.MemberService;
import capstone.ApplePie_Spring.Team.service.TeamService;
import capstone.ApplePie_Spring.Team.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "teams API", description = "teams 관련된 API")

@RequestMapping("/teams")
@RestController
@RequiredArgsConstructor
public class TeamController {
    
    private final TeamService teamService;
    private final VolunteerService volunteerService;
    private final MemberService memberService;

    // 팀 생성
    @PostMapping("/{uid}")
    @Operation(summary = "팀 생성")
    public ResponseEntity<Object> createTeam(@PathVariable Long uid, @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.save(uid, teamDto), HttpStatus.OK);
    }

    // 팀 수정
    @PutMapping("/{uid}")
    @Operation(summary = "팀 수정")
    public ResponseEntity<Object> updateTeam(@PathVariable Long uid, @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.update(uid, teamDto), HttpStatus.OK);
    }

    // 팀 모집 취소
    @DeleteMapping("/{uid}")
    @Operation(summary = "팀 모집 취소")
    public ResponseEntity<Object> cancelTeam(@PathVariable Long uid, @RequestBody TeamUpdateDto teamUpdateDto) {
        return new ResponseEntity<>(teamService.cancel(uid, teamUpdateDto), HttpStatus.OK);
    }

    // 팀에 지원
    @PostMapping("/volunteer")
    @Operation(summary = "팀 지원")
    public ResponseEntity<Object> createVolunteer(@RequestBody VolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.save(volunteerDto), HttpStatus.OK);
    }

    // 지원 취소
    @DeleteMapping("/volunteer")
    @Operation(summary = "팀 지원 취소")
    public ResponseEntity<Object> cancelVolunteer(@RequestBody CancelVolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.cancel(volunteerDto), HttpStatus.OK);
    }

    // 관련된 모든 팀 uid -> teamId
    @GetMapping("/{uid}")
    @Operation(summary = "관련 팀 모두 조회")
    public ResponseEntity<Object> getMyTeam(@PathVariable Long uid) {
        return new ResponseEntity<>(teamService.findTeam(uid), HttpStatus.OK);
    }

    // 지원자 리스트 조회
    @PostMapping("/volunteer/{uid}") //get
    @Operation(summary = "팀 지원자 리스트 조회")
    public ResponseEntity<Object> findVolunteers(@PathVariable Long uid, @RequestBody FindVolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.getVolunteers(uid, volunteerDto), HttpStatus.OK);
    }

    // 팀원 처리
    @PostMapping("/member")
    @Operation(summary = "팀원 처리")
    public ResponseEntity<Object> updateVolunteerToMember(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.OK);
    }

    // 팀의 팀원들 조회
    @PostMapping("/member/all") // get
    @Operation(summary = "팀원 조회")
    public ResponseEntity<Object> getMember(@RequestBody FindMemberDto findMemberDto) {
        return new ResponseEntity<>(memberService.getMembers(findMemberDto), HttpStatus.OK);
    }

}
