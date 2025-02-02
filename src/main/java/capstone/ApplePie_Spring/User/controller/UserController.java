package capstone.ApplePie_Spring.User.controller;

import capstone.ApplePie_Spring.Board.service.BoardService;
import capstone.ApplePie_Spring.Profiles.service.ProfilesService;
import capstone.ApplePie_Spring.User.dto.LoginDto;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;
import capstone.ApplePie_Spring.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users API", description = "users 관련된 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ProfilesService profilesService;
    private final BoardService boardService;

    // 회원가입
    @PostMapping(value = "/signup")
    @Operation(summary = "회원가입")
    // consumes = "application/x-www-form-urlencoded"
    public ResponseEntity<Object> signup(@RequestBody SignupDto signupDto) throws Exception {
        return new ResponseEntity<>(userService.signup(signupDto), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{uid}")
    @Operation(summary = "회원 탈퇴")
    public ResponseEntity<Object> deleteUser(@PathVariable Long uid) {
        return new ResponseEntity<>(userService.delete(uid), HttpStatus.OK);
    }

    // 회원 정보 조회
    @GetMapping("profile/{uid}")
    @Operation(summary = "회원 정보 조회")
    public ResponseEntity<Object> findProfile(@PathVariable Long uid) {
        return new ResponseEntity<>(userService.findProfile(uid), HttpStatus.OK);
    }

    // 회원 정보 수정
    @PutMapping("profile/{uid}")
    @Operation(summary = "회원 정보 수정")
    public ResponseEntity<Object> findProfile(@PathVariable Long uid, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(userService.updateProfile(uid, profileDto), HttpStatus.OK);
    }

    // 개인 프로필 전체 조회 - 4개
    @GetMapping("profiles/{pid}")
    @Operation(summary = "개인 프로필 전체 조회")
    public ResponseEntity<Object> findUserProfiles(@PathVariable Long pid) {
        return new ResponseEntity<>(profilesService.findUserProfiles(pid), HttpStatus.OK);
    }

    // 회원정보와 개인 프로필 정보 조회
    @GetMapping("profiles/all/{pid}")
    @Operation(summary = "회원정보와 개인 프로필 정보 조회")
    public ResponseEntity<Object> findAllUserProfiles(@PathVariable Long pid) {
        return new ResponseEntity<>(profilesService.findAllProfiles(pid), HttpStatus.OK);
    }


    // 작성한 글 조회
    @GetMapping("/board/{uid}")
    @Operation(summary = "작성한 글 조회")
    public ResponseEntity<Object> getBoards(@PathVariable Long uid) {
        return new ResponseEntity<>(boardService.myBoardPagesBy(uid), HttpStatus.OK);
    }


}
