package capstone.ApplePie_Spring.User.controller;

import capstone.ApplePie_Spring.User.dto.LoginDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;
import capstone.ApplePie_Spring.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public String test() {
        return "ApplePie!";
    }

    // 회원가입
    @PostMapping("/users/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(userService.signup(signupDto), HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
