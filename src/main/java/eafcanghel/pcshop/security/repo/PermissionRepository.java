package eafcanghel.pcshop.security.repo;

import eafcanghel.pcshop.security.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
