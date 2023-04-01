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

    @Override
    public Object signup(SignupDto signupDto) {
        if (validateDuplicateEmail(signupDto.getEmail())) { // email 중복
            return new ResponseUser(ExceptionCode.SIGNUP_DUPLICATED_EMAIL);
        } else if (validateDuplicateNickname(signupDto.getNickname())) { // nickname 중복
            return new ResponseUser(ExceptionCode.SIGNUP_DUPLICATED_NICKNAME);
        }

        try {
            String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
            signupDto.setPassword(encodedPassword);
            userRepository.save(signupDto.toEntity(signupDto));
            System.out.println("UserServiceImpl.signup");
            return new ResponseUser(ExceptionCode.SIGNUP_CREATED_OK);
        } catch (Exception exception) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }
    }

    @Override
    public Object login(LoginDto loginDto) {
        Optional<User> findUser = userRepository.findByEmailAndStatus(loginDto.getEmail(), 1);
        if (findUser.isEmpty()) {
            return new ResponseLogin(ExceptionCode.LOGIN_NOT_FOUND_EMAIL, null);
        }

        User user = findUser.get();
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) { // 비밀번호 일치
            System.out.println("UserServiceImpl.login");
            return new ResponseLogin(ExceptionCode.LOGIN_OK, user.getId());
        }
        return new ResponseLogin(ExceptionCode.LOGIN_NOT_FOUND_PW, null);
    }

    @Override
    public Object delete(Long userId) {
        Optional<User> findUser = userRepository.findByIdAndStatus(userId, 1);
        if (findUser.isEmpty()) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }
        User user = findUser.get();
        user.delete();
        return new ResponseUser(ExceptionCode.WITHDRAW_USER_OK);
    }

    private boolean validateDuplicateEmail(String email) {
        Optional<User> findUser = userRepository.findByEmailAndStatus(email, 1);
        return findUser.isPresent();
    }

    private boolean validateDuplicateNickname(String nickname) {
        Optional<User> findUser = userRepository.findByNicknameAndStatus(nickname, 1);
        return findUser.isPresent();
    }
}
