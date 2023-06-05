package eafcanghel.pcshop.security.user;

import eafcanghel.pcshop.security.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
//_user so that there is no conflict with Spring USER table
@Table(name = "_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @NonNull
    @Size(max = 50)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @NonNull
    @Size(max = 50)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @NonNull
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @NonNull
    @Size(min = 6, max = 100)
    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;
    @NonNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Timestamp dob;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private Role role;

}