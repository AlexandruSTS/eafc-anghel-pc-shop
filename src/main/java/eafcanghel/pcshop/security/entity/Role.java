package eafcanghel.pcshop.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(name = "NAME")
    private String name;
    @NonNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "ROLE_PERMISSION_ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_PERMISSION_PERMISSION_ID"))
    private List<Permission> permissions;
}
