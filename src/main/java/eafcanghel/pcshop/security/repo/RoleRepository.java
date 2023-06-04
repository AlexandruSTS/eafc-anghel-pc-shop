package eafcanghel.pcshop.security.repo;

import eafcanghel.pcshop.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
