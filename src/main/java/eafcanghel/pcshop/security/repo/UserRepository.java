package eafcanghel.pcshop.security.repo;

import eafcanghel.pcshop.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
