package IliaKlimko.repos;

import IliaKlimko.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @NotBlank(message = "username canot be empty")
    User findByUsername(String username);
    User findUserById(Long id);
    User findByActivationCode(String activationCode);

    User findUserByPassword(String password);
}
