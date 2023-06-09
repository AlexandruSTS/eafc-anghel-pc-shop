package eafcanghel.pcshop.item;

import eafcanghel.pcshop.order.OrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;

    @ElementCollection
    @CollectionTable(name = "ITEM_CATEGORY",
            joinColumns = @JoinColumn(name = "ITEM_ID"))
    @Column(name = "CATEGORY_ID")
    private Set<Integer> categoryIds;

}

//public class OrderLine {
//    private Long id;
//    private int quantity;
//    private Item item;
//    private Order order;
//
//    // Constructors, getters, and setters
//
//    // ...
//}