package eafcanghel.pcshop.order;

import eafcanghel.pcshop.payment.Payment;
import eafcanghel.pcshop.payment.PaymentMethod;
import eafcanghel.pcshop.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private User customer;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;

    @OneToOne()
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @OneToMany(mappedBy = "orderId",cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new ArrayList<>();
        }
        orderLines.add(orderLine);
        orderLine.setOrderId(this);
    }

    public void removeOrderLine(OrderLine orderLine) {
        if (orderLines != null) {
            orderLines.remove(orderLine);
            orderLine.setOrderId(null);
        }
    }
}
