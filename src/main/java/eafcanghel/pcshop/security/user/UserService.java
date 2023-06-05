package eafcanghel.pcshop.security.user;

import eafcanghel.pcshop.security.enums.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );

    }

    public void registerUser(UserRegistrationDto registrationDto) {
        // Check if a user with the same email already exists
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {

            User user = userRepository.findByEmail(registrationDto.getEmail()).get();
            var auth = user.getRole().getAuthorities();

            System.out.println();
            System.out.println(user.getRole().getPermissions());

            throw new IllegalArgumentException("User with the provided email already exists");
        }

        // Create a new user
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setDob(this.getTimestamp(registrationDto.getDob()));
        user.setRole(Role.ROLE_MANAGER);

        // Save the user to the database
        userRepository.save(user);
    }

    public void loginUser(UserLoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

    }

    public Timestamp getTimestamp(java.util.Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }
}


