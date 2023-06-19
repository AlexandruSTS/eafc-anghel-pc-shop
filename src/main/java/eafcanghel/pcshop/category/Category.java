package eafcanghel.pcshop.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull(message = "Category name cannot be null")
    @Size(min = 3, max = 255)
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "Category description cannot be null")
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

}
