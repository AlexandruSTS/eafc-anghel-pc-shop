package eafcanghel.pcshop.security.user;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegistrationDto {

    @NotBlank(message = "First name can't be blank")
    @Pattern(regexp = "^[A-Za-z\\-]+$", message = "First name can only contain alphabetic characters and hyphens")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Pattern(regexp = "^[A-Za-z\\-]+$", message = "Last name can only contain alphabetic characters and hyphens")
    private String lastName;
    @Email
    @NotBlank(message = "Email can't be blank")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter and one digit")
    private String password;

    @Past
    @DateTimeFormat
    private Date dob;

}
