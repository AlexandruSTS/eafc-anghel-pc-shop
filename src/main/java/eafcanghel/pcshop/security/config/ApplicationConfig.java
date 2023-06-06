package eafcanghel.pcshop.security.config;

import eafcanghel.pcshop.security.user.User;
import eafcanghel.pcshop.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ApplicationConfig {
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Bean
    AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                String password = authentication.getCredentials().toString();

                // Retrieve the user from the repository based on the provided username
                Optional<User> userOptional = userRepository.findByEmail(username);
                if (userOptional.isEmpty()) {
                    throw new BadCredentialsException("Invalid username or password");
                }

                User user = userOptional.get();

                // Compare the provided password with the stored password hash
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new BadCredentialsException("Invalid username or password");
                }

                // Create a list of authorities (roles) for the authenticated user
                List<GrantedAuthority> authorities = user.getRole().getAuthorities().stream()
                        .map(role -> new SimpleGrantedAuthority(role.toString()))
                        .collect(Collectors.toList());

                // Return the authenticated token
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
        };
    }
}
