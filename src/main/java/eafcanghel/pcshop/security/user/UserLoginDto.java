package eafcanghel.pcshop.security.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginDto {
    @Email
    @NotBlank(message = "Email can't be blank")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter and one digit")
    private String password;

}
