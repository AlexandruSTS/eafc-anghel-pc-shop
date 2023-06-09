package eafcanghel.pcshop.service;

import eafcanghel.pcshop.order.Order;
import eafcanghel.pcshop.order.OrderLine;
import eafcanghel.pcshop.order.OrderLineRepository;
import eafcanghel.pcshop.order.OrderRepository;
import eafcanghel.pcshop.payment.Payment;
import eafcanghel.pcshop.payment.PaymentMethod;
import eafcanghel.pcshop.payment.PaymentRepository;
import eafcanghel.pcshop.user.User;
import eafcanghel.pcshop.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EntityDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    private final OrderLineRepository orderLineRepository;

    @Override
    public void run(String... args) {

        // Create and save users
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password1");
        user1.setDob(Date.valueOf("1990-01-01"));

        List<Order> orders = new ArrayList<>();

        Order order1 = new Order();
        order1.setCustomer(user1);
        order1.setOrderDate(LocalDateTime.now());
        order1.setTotalAmount(BigDecimal.valueOf(100.0));
        order1.setStatus("Pending");
        order1.setShippingAddress("123 Street, City");

        OrderLine orderLine1_1 = new OrderLine();
        orderLine1_1.setOrderId(order1);
        orderLine1_1.setQuantity(3);

        OrderLine orderLine1_2 = new OrderLine();
        orderLine1_2.setOrderId(order1);
        orderLine1_2.setQuantity(1);

        order1.setOrderLines(List.of(orderLine1_1));
        orders.add(order1);

        List<Payment> payments = new ArrayList<>();

        Payment payment1 = new Payment();
        payment1.setPAYMENT_METHOD(PaymentMethod.MC);
        payment1.setPaymentDate(LocalDateTime.now());
        payment1.setAmount(BigDecimal.valueOf(100.0));
        payments.add(payment1);

        order1.setPayment(payment1);

        userRepository.save(user1);

        paymentRepository.saveAll(payments);

        orderRepository.saveAll(orders);

    }
}

