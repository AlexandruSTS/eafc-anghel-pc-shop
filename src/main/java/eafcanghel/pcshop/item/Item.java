package eafcanghel.pcshop.item;

import eafcanghel.pcshop.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 100000, message = "Price cannot exceed 100000")
    private Double price;

    @NotEmpty(message = "Categories cannot be null")
    @ManyToMany
    Set<Category> categories;
}
