package eafcanghel.pcshop.service;

import eafcanghel.pcshop.security.entity.Role;
import eafcanghel.pcshop.security.entity.User;
import eafcanghel.pcshop.security.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MyDbService {
    private final UserRepository userRepository;
    public MyDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveData() {

        Role adminRole = Role.ROLE_ADMIN;
        Role userRole = Role.ROLE_USER;

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


        userRepository.save(user1);
        userRepository.save(user2);
//        userRepository.deleteById(user1.getId());
//        roleRepository.deleteById(adminRole.getId());

    }
}
