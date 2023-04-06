package capstone.ApplePie_Spring.User.controller;

import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profile")
public class ProfileController {

    private final ProfileService profileService;

    // 회원 프로필 조회
    @GetMapping("/{id}")
    public ResponseEntity<Object> findProfile(@PathVariable Long id) {
        return new ResponseEntity<>(profileService.find(id), HttpStatus.OK);
    }

    // 프로필 생성
    @PostMapping("/{id}")
    public ResponseEntity<Object> saveProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.save(id, profileDto), HttpStatus.OK);
    }

    // 프로필 수정
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.update(id, profileDto), HttpStatus.OK);
    }
}
