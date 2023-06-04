package eafcanghel.pcshop.service;

import eafcanghel.pcshop.security.entity.Permission;
import eafcanghel.pcshop.security.entity.Role;
import eafcanghel.pcshop.security.entity.User;
import eafcanghel.pcshop.security.repo.PermissionRepository;
import eafcanghel.pcshop.security.repo.RoleRepository;
import eafcanghel.pcshop.security.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

        Role adminRole = new Role("admin", "the admin role");
        Role userRole = new Role("user", "the user role");


        List<Permission> adminPerms = new ArrayList<Permission>();
        adminPerms.add(writePerm);
        adminPerms.add(readPerm);
        adminPerms.add(catalogPerm);
        adminRole.setPermissions(adminPerms);

        List<Permission> userPerms = new ArrayList<Permission>();
        userPerms.add(writePerm);
        userPerms.add(readPerm);
        userRole.setPermissions(userPerms);

        User user1 = new User(
                "Alex",
                "Anghel",
                "alex@email.com",
                "psw",
                new Timestamp(System.currentTimeMillis()),
                adminRole
        );

        User user2 = new User(
                "John",
                "Doe",
                "jd@email.com",
                "psw2",
                new Timestamp(System.currentTimeMillis()),
                userRole
        );

        permissionRepository.save(writePerm);
        permissionRepository.save(readPerm);
        permissionRepository.save(catalogPerm);

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        userRepository.save(user1);
        userRepository.save(user2);

//        userRepository.deleteById(user1.getId());
//        roleRepository.deleteById(adminRole.getId());
        permissionRepository.deleteById(catalogPerm.getId());


    }

//    public void deletePerm(Integer id) {
//        userRepository.de
//    }
}
