package eafcanghel.pcshop.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ROLE_PERMISSION", joinColumns=@JoinColumn(name="ROLE_PERMISSION_PERMISSION_ID"), inverseJoinColumns=@JoinColumn(name="ROLE_PERMISSION_ROLE_ID"))
    private List<Permission> permissions;
}
