package eafcanghel.pcshop.service;

import eafcanghel.pcshop.category.Category;
import eafcanghel.pcshop.category.CategoryRepository;
import eafcanghel.pcshop.item.Item;
import eafcanghel.pcshop.item.ItemRepository;
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
import java.util.Set;

@Component
@AllArgsConstructor
public class EntityDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public void run(String... args) {

        // Create and save user
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password1");
        user1.setDob(Date.valueOf("1990-01-01"));

        //Create new order
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setCustomer(user1);
        order1.setOrderDate(LocalDateTime.now());
        order1.setTotalAmount(BigDecimal.valueOf(100.0));
        order1.setStatus("Pending");
        order1.setShippingAddress("123 Street, City");

        //Create categories
        List<Category> categories = new ArrayList<>();
        Category pc_category = new Category();
        pc_category.setName("LAPTOP");
        pc_category.setDescription("The laptops");

        Category windows_category = new Category();
        windows_category.setName("WINDOWS");
        windows_category.setDescription("Windows OS");

        Category components = new Category();
        components.setName("COMPONENTS");
        components.setDescription("All components");

        Category gpus = new Category();
        gpus.setName("GRAPHICS_CARDS");
        gpus.setDescription("All graphics cards");

        //Items
        List<Item> items = new ArrayList<>();
        Item hp_elitebook = new Item();
        hp_elitebook.setName("HP ELITEBOOK v3");
        hp_elitebook.setDescription("The best laptop from HP");
        hp_elitebook.setPrice(BigDecimal.valueOf(1290.89));
        hp_elitebook.setCATEGORIES(Set.of(pc_category, windows_category));

        Item nvidia_geforce = new Item();
        nvidia_geforce.setName("NVIDIA GFORCE 1040");
        nvidia_geforce.setDescription("The best GPU from Nvidia");
        nvidia_geforce.setPrice(BigDecimal.valueOf(999));
        nvidia_geforce.setCATEGORIES(Set.of(components, gpus));

        categories.add(pc_category);
        categories.add(windows_category);
        categories.add(components);
        categories.add(gpus);

        items.add(hp_elitebook);
        items.add(nvidia_geforce);

        //Order lines
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine orderLine1_1 = new OrderLine();
        orderLine1_1.setOrderId(order1);
        orderLine1_1.setQuantity(1);
        orderLine1_1.setItemId(hp_elitebook);

        OrderLine orderLine1_2 = new OrderLine();
        orderLine1_2.setOrderId(order1);
        orderLine1_2.setQuantity(2);
        orderLine1_2.setItemId(nvidia_geforce);

        orderLines.add(orderLine1_1);
        orderLines.add(orderLine1_2);

        //Set order lines to order
        order1.setOrderLines(List.of(orderLine1_1, orderLine1_2));
        orders.add(order1);

        //Payments
        List<Payment> payments = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setPAYMENT_METHOD(PaymentMethod.MC);
        payment1.setPaymentDate(LocalDateTime.now());
        payment1.setAmount(BigDecimal.valueOf(100.0));
        payments.add(payment1);

        //Set payment to order
        order1.setPayment(payment1);

        categoryRepository.saveAll(categories);
        itemRepository.saveAll(items);
        userRepository.save(user1);
        paymentRepository.saveAll(payments);
        orderRepository.saveAll(orders);
        orderLineRepository.saveAll(orderLines);

    }
}

