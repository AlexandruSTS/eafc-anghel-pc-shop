package eafcanghel.pcshop.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PcShopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Size(max = 50)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Size(max = 50)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Size(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private String email;

    @JsonIgnore
    @Size(min = 6, max = 100)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private Date dob;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


}