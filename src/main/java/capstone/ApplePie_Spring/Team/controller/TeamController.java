package capstone.ApplePie_Spring.Team.controller;


import capstone.ApplePie_Spring.Team.dto.*;
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
    public ResponseEntity<Object> createTeam(@RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.save(teamDto), HttpStatus.OK);
    }

    // 팀 수정
    @PutMapping
    public ResponseEntity<Object> updateTeam(@RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.update(teamDto), HttpStatus.OK);
    }

/*    // 팀 모집 완료
    @PutMapping
    public ResponseEntity<Object> completeTeam(@RequestBody TeamUpdateDto teamUpdateDto) {
        return new ResponseEntity<>(teamService.complete(teamUpdateDto), HttpStatus.OK);
    }*/

    // 팀 모집 취소
    @DeleteMapping
    public ResponseEntity<Object> cancelTeam(@RequestBody TeamUpdateDto teamUpdateDto) {
        return new ResponseEntity<>(teamService.cancel(teamUpdateDto), HttpStatus.OK);
    }

    // 팀에 지원
    @PostMapping("/volunteer")
    public ResponseEntity<Object> createVolunteer(@RequestBody VolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.save(volunteerDto), HttpStatus.OK);
    }

    // 지원 취소
    @DeleteMapping("/volunteer")
    public ResponseEntity<Object> cancelVolunteer(@RequestBody CancelVolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.cancel(volunteerDto), HttpStatus.OK);
    }

    // 팀원 처리
    @PostMapping("/member")
    public ResponseEntity<Object> updateVolunteerToMember(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.OK);
    }

    // 팀원 취소

    // 관련된 모든 팀 -> teamId
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMyTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.findTeam(id), HttpStatus.OK);
    }

    // 자기가 생성한 팀이면, 지원자 리스트 조회 -> volunteerId
    @GetMapping("/volunteer")
    public ResponseEntity<Object> findVolunteers(@RequestBody FindVolunteerDto volunteerDto) {
        return new ResponseEntity<>(volunteerService.getVolunteers(volunteerDto), HttpStatus.OK);
    }

}
