package capstone.ApplePie_Spring.User.service;

import capstone.ApplePie_Spring.User.Response.ResponseFindUser;
import capstone.ApplePie_Spring.User.Response.ResponseUser;
import capstone.ApplePie_Spring.User.domain.Profile;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.dto.ProfileDto;
import capstone.ApplePie_Spring.User.repository.ProfileRepository;
import capstone.ApplePie_Spring.User.repository.UserRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    private static final Integer STATUS = 1;

    @Override
    public Object find(Long userId) {
        Optional<Profile> findProfile = profileRepository.findByUserIdAndStatus(userId, STATUS);
        if (findProfile.isEmpty()) {
            Optional<User> findUser = userRepository.findByIdAndStatus(userId, STATUS);
            return new ResponseFindUser(ExceptionCode.PROFILE_FIND_OK, findUser.get(), null);
        }
        Profile profile = findProfile.get();
        User user = profile.getUser();
        return new ResponseFindUser(ExceptionCode.PROFILE_FIND_OK, user, profile);
    }

    @Override
    public Object save(Long userId, ProfileDto profileDto) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }

        boolean existProfile = profileRepository.existsByUserIdAndStatus(userId, STATUS);
        if (existProfile) {
            return new ResponseUser(ExceptionCode.PROFILE_CREATED_ERROR);
        }

        Profile profile = profileDto.toProfile(findUser.get());
        profileRepository.save(profile);
        return new ResponseUser(ExceptionCode.PROFILE_CREATED_OK);
    }

    @Override
    public Object update(Long userId, ProfileDto profileDto) {
        Optional<Profile> findProfile = profileRepository.findByUserIdAndStatus(userId, STATUS);
        if (findProfile.isEmpty()) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }

        try {
            Profile profile = findProfile.get();
            profile.update(profileDto); // 변경 감지
            return new ResponseUser(ExceptionCode.USER_UPDATE_OK);
        } catch (Exception exception) {
            return new ResponseUser(ExceptionCode.USER_ERROR);
        }
    }

    private boolean validateUser(Long userId) {
        Optional<User> findUser = userRepository.findByIdAndStatus(userId, STATUS);
        return findUser.isPresent();
    }
}
