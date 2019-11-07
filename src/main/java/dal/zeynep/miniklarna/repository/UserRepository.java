package dal.zeynep.miniklarna.repository;

import dal.zeynep.miniklarna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
