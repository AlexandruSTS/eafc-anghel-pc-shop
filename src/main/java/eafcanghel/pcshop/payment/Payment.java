package eafcanghel.pcshop.payment;

//import eafcanghel.pcshop.order.Order;
import eafcanghel.pcshop.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod PAYMENT_METHOD;

    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

}
