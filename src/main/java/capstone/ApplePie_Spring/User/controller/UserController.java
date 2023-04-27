package capstone.ApplePie_Spring.User.controller;

import capstone.ApplePie_Spring.User.dto.LoginDto;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;
import capstone.ApplePie_Spring.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String test() {
        return "ApplePie!";
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupDto signupDto) throws Exception {
        return new ResponseEntity<>(userService.signup(signupDto), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    // 회원 정보 조회
    @GetMapping("profile/{id}")
    public ResponseEntity<Object> findProfile(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findProfile(id), HttpStatus.OK);
    }

    // 회원 정보 수정
    @PutMapping("profile/{id}")
    public ResponseEntity<Object> findProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(userService.updateProfile(id, profileDto), HttpStatus.OK);
    }

}
