package capstone.ApplePie_Spring.Profiles.controller;

import capstone.ApplePie_Spring.Profiles.dto.*;
import capstone.ApplePie_Spring.Profiles.service.ProfilesService;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "profiles API", description = "profiles 관련된 API")
@RequiredArgsConstructor
@RequestMapping("/profiles")
@RestController
public class ProfilesController {

    private final ProfilesService profilesService;

    @PostMapping // get
    @Operation(summary = "프로필 전체 조회")
    public ResponseEntity<Object> findProject(@RequestBody ProfilesFindDto profilesFindDto) {
        return new ResponseEntity<>(profilesService.findProfiles(profilesFindDto), HttpStatus.OK);
    }

    @PostMapping("/one") // get
    @Operation(summary = "프로필 하나 조회")
    public ResponseEntity<Object> findProject(@RequestBody OneProfilesFindDto oneProfilesFindDto) {
        return new ResponseEntity<>(profilesService.findOneProfiles(oneProfilesFindDto), HttpStatus.OK);
    }


    @PostMapping("/{pid}")
    @Operation(summary = "프로필 저장")
    public ResponseEntity<Object> saveProject(@PathVariable Long pid, @RequestBody ProfilesDto profilesDto) {
        return new ResponseEntity<>(profilesService.saveProfiles(pid, profilesDto), HttpStatus.OK);
    }

    @PutMapping("/{pid}")
    @Operation(summary = "프로필 전체 수정")
    public ResponseEntity<Object> updateProject(@PathVariable Long pid, @RequestBody ProfilesDto profilesDto) {
        return new ResponseEntity<>(profilesService.updateProfiles(pid, profilesDto), HttpStatus.OK);
    }

    // open 상태 변경
    @PutMapping("/open/{pid}")
    @Operation(summary = "프로필 상태 변경")
    public ResponseEntity<Object> updateOpenLesson(@PathVariable Long pid, @RequestBody OpenDto openDto) {
        return new ResponseEntity<>(profilesService.openProfiles(pid, openDto), HttpStatus.OK);
    }
}
