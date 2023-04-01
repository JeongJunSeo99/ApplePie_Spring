package capstone.ApplePie_Spring.User.service;

import capstone.ApplePie_Spring.User.dto.ProfileDto;

public interface ProfileService {
    Object find(Long userId);
    Object save(Long userId, ProfileDto profileDto);
    Object update(Long userId, ProfileDto profileDto);
}
