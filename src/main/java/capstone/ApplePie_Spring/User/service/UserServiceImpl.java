package capstone.ApplePie_Spring.User.service;

import capstone.ApplePie_Spring.User.Response.ResponseLogin;
import capstone.ApplePie_Spring.User.Response.ResponseUser;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.dto.LoginDto;
import capstone.ApplePie_Spring.User.dto.SignupDto;
import capstone.ApplePie_Spring.User.repository.ProfileRepository;
import capstone.ApplePie_Spring.User.repository.UserRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    private static final Integer STATUS = 1;

    @Override
    public Object signup(SignupDto signupDto) {
        if (validateDuplicateEmail(signupDto.getEmail())) { // email 중복
            return new ResponseUser(ExceptionCode.SIGNUP_DUPLICATED_EMAIL);
        } else if (validateDuplicateNickname(signupDto.getNickname())) { // nickname 중복
            return new ResponseUser(ExceptionCode.SIGNUP_DUPLICATED_NICKNAME);
        }

        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
        signupDto.setPassword(encodedPassword);
        userRepository.save(signupDto.toUser());
        return new ResponseUser(ExceptionCode.SIGNUP_CREATED_OK);
    }

    @Override
    public Object login(LoginDto loginDto) {
        Optional<User> findUser = userRepository.findByEmailAndStatus(loginDto.getEmail(), STATUS);
        if (findUser.isEmpty()) {
            return new ResponseUser(ExceptionCode.LOGIN_NOT_FOUND_EMAIL);
        }

        User user = findUser.get();
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) { // 비밀번호 일치
            return new ResponseLogin(ExceptionCode.LOGIN_OK, user.getId());
        }
        return new ResponseUser(ExceptionCode.LOGIN_NOT_FOUND_PW);
    }

    @Override
    public Object delete(Long userId) {
        Optional<User> findUser = userRepository.findByIdAndStatus(userId, STATUS);
        if (findUser.isEmpty()) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }
        User user = findUser.get();
        user.delete();
        return new ResponseUser(ExceptionCode.WITHDRAW_USER_OK);
    }

    private boolean validateDuplicateEmail(String email) {
        return userRepository.existsByEmailAndStatus(email, STATUS);
    }

    private boolean validateDuplicateNickname(String nickname) {
        return userRepository.existsByNicknameAndStatus(nickname, STATUS);
    }
}
