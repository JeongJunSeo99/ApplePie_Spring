package capstone.ApplePie_Spring.User.repository;

import capstone.ApplePie_Spring.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndStatus(Long id, int status);
    Optional<User> findByEmailAndStatus(String email, int status);
    Optional<User> findByNicknameAndStatus(String email, int status);
    User save(User user);
}
