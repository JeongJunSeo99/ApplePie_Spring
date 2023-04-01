package capstone.ApplePie_Spring.User.service;

import capstone.ApplePie_Spring.User.dto.LoginDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;

public interface UserService {
    Object signup(SignupDto signupDto);
    Object login(LoginDto loginDto);
    Object delete(Long userId);
}
