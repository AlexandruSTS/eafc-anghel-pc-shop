package eafcanghel.pcshop.service;

import eafcanghel.pcshop.security.entity.Permission;
import eafcanghel.pcshop.security.entity.Role;
import eafcanghel.pcshop.security.repo.PermissionRepository;
import eafcanghel.pcshop.security.repo.RoleRepository;
import eafcanghel.pcshop.security.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDbService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public MyDbService(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public void saveData() {

        Permission writePerm = new Permission("write", "the write permission");
        Permission readPerm = new Permission("read", "the read permission");
        Permission catalogPerm = new Permission("catalog", "the catalog permission");

        Role admin = new Role("admin", "the admin role");
        Role user = new Role("user", "the user role");

        List<Permission> adminPerms = new ArrayList<Permission>();
        adminPerms.add(writePerm);
        adminPerms.add(readPerm);
        adminPerms.add(catalogPerm);
        admin.setPermissions(adminPerms);

        List<Permission> userPerms = new ArrayList<Permission>();
        userPerms.add(writePerm);
        userPerms.add(readPerm);
        user.setPermissions(userPerms);

        permissionRepository.save(writePerm);
        permissionRepository.save(readPerm);
        permissionRepository.save(catalogPerm);

        roleRepository.save(admin);
        roleRepository.save(user);

        permissionRepository.deleteById(2);
    }
}
