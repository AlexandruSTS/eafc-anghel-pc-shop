package eafcanghel.pcshop.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
//_user so that there is no conflict with Spring USER table
@Table(name = "_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(name = "FIRST_NAME")
    private String firstName;
    @NonNull
    @Column(name = "LAST_NAME")
    private String lastName;
    @NonNull
    @Column(name = "EMAIL")
    private String email;
    @NonNull
    private String password;
    @NonNull
    @Column(name = "DATE_OF_BIRTH")
    private Date dob;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ROLE_ID")
    private Role role;
}