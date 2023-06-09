package eafcanghel.pcshop.user;

//import eafcanghel.pcshop.order.Order;
import eafcanghel.pcshop.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//_user so that there is no conflict with Spring USER table
@Table(name = "_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Size(max = 50)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Size(min = 6, max = 100)
    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dob;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Order> orders;

//    @NonNull
//    @Enumerated(EnumType.STRING)
//    @Column(name = "USER_ROLE")
//    private Role role;

}