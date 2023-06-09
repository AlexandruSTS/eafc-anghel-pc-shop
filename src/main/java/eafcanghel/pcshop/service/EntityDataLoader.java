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

        // Create user
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password1");
        user1.setDob(Date.valueOf("1990-01-01"));

        User user2 = new User();
        user2.setFirstName("Hellen");
        user2.setLastName("Sully");
        user2.setEmail("hellen.sully@example.com");
        user2.setPassword("password1");
        user2.setDob(Date.valueOf("1995-06-01"));

        User user3 = new User();
        user3.setFirstName("Nina");
        user3.setLastName("Connor");
        user3.setEmail("nina.conner@example.com");
        user3.setPassword("password1");
        user3.setDob(Date.valueOf("1955-06-05"));

        users.add(user1);
        users.add(user2);
        users.add(user3);

        //Create order
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setCustomer(user1);
        order1.setOrderDate(LocalDateTime.now());
        order1.setStatus("Pending");
        order1.setShippingAddress("123 Street, City");

        Order order2 = new Order();
        order2.setCustomer(user2);
        order2.setOrderDate(LocalDateTime.now());
        order2.setStatus("Completed");
        order2.setShippingAddress("356 Street, City");

        Order order3 = new Order();
        order3.setCustomer(user1);
        order3.setOrderDate(LocalDateTime.now());
        order3.setStatus("In transit");
        order3.setShippingAddress("458 Street, City");

        //Create categories
        List<Category> categories = new ArrayList<>();

        Category laptop_category = new Category();
        laptop_category.setName("LAPTOP");
        laptop_category.setDescription("The laptops");

        Category windows_category = new Category();
        windows_category.setName("WINDOWS");
        windows_category.setDescription("Windows OS");

        Category components_category = new Category();
        components_category.setName("COMPONENTS");
        components_category.setDescription("All components");

        Category gpus_category = new Category();
        gpus_category.setName("GRAPHICS_CARDS");
        gpus_category.setDescription("All graphics cards");

        Category apple_category = new Category();
        apple_category.setName("APPLE");
        apple_category.setDescription("All apple products");

        Category intel_category = new Category();
        intel_category.setName("INTEL");
        intel_category.setDescription("All Intel products");

        Category keyboard_category = new Category();
        keyboard_category.setName("KEYBOARDS");
        keyboard_category.setDescription("All keyboards");

        Category macbooks_category = new Category();
        macbooks_category.setName("MACBOOKS");
        macbooks_category.setDescription("All MacBooks");

        //Items
        List<Item> items = new ArrayList<>();

        Item hp_elitebook = new Item();
        hp_elitebook.setName("HP ELITEBOOK v3");
        hp_elitebook.setDescription("The best laptop from HP");
        hp_elitebook.setPrice(1290.89);
        hp_elitebook.setCATEGORIES(Set.of(laptop_category, windows_category));

        Item nvidia_geforce = new Item();
        nvidia_geforce.setName("NVIDIA GFORCE 1040");
        nvidia_geforce.setDescription("The best GPU from Nvidia");
        nvidia_geforce.setPrice(999.99);
        nvidia_geforce.setCATEGORIES(Set.of(components_category, gpus_category));

        Item macbook_air = new Item();
        macbook_air.setName("MacBook Pro Intel");
        macbook_air.setDescription("The best Macbook Pro");
        macbook_air.setPrice(1690.57);
        macbook_air.setCATEGORIES(Set.of(laptop_category, apple_category, intel_category));

        Item macbook_pro = new Item();
        macbook_pro.setName("MacBook Air M1");
        macbook_pro.setDescription("The best Macbook Air");
        macbook_pro.setPrice(5270.62);
        macbook_pro.setCATEGORIES(Set.of(laptop_category, apple_category));

        Item steelSeries_apex = new Item();
        steelSeries_apex.setName("Steel Series Apex");
        steelSeries_apex.setDescription("The best keyboard from SteelSeries");
        steelSeries_apex.setPrice(290.59);
        steelSeries_apex.setCATEGORIES(Set.of(laptop_category, apple_category));

        categories.add(laptop_category);
        categories.add(windows_category);
        categories.add(components_category);
        categories.add(gpus_category);
        categories.add(apple_category);
        categories.add(intel_category);
        categories.add(macbooks_category);
        categories.add(keyboard_category);

        items.add(hp_elitebook);
        items.add(nvidia_geforce);
        items.add(macbook_air);
        items.add(macbook_pro);
        items.add(steelSeries_apex);

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

        OrderLine orderLine2_1 = new OrderLine();
        orderLine2_1.setOrderId(order2);
        orderLine2_1.setQuantity(1);
        orderLine2_1.setItemId(macbook_pro);

        OrderLine orderLine3_1 = new OrderLine();
        orderLine3_1.setOrderId(order3);
        orderLine3_1.setQuantity(1);
        orderLine3_1.setItemId(steelSeries_apex);

        OrderLine orderLine3_2 = new OrderLine();
        orderLine3_2.setOrderId(order2);
        orderLine3_2.setQuantity(2);
        orderLine3_2.setItemId(macbook_air);

        Double order1Total = 0.00;
        order1Total += this.calculateLineTotal(orderLine1_1.getQuantity(), hp_elitebook.getPrice());
        order1Total += this.calculateLineTotal(orderLine1_2.getQuantity(), nvidia_geforce.getPrice());
        order1.setTotalAmount(order1Total);

        Double order2Total = this.calculateLineTotal(orderLine2_1.getQuantity(), macbook_pro.getPrice());
        order2.setTotalAmount(order2Total);

        Double order3Total = 0.00;
        order3Total += this.calculateLineTotal(orderLine3_1.getQuantity(), steelSeries_apex.getPrice());
        order3Total += this.calculateLineTotal(orderLine3_2.getQuantity(), macbook_air.getPrice());
        order3.setTotalAmount(order3Total);

        orderLines.add(orderLine1_1);
        orderLines.add(orderLine1_2);
        orderLines.add(orderLine2_1);
        orderLines.add(orderLine3_1);
        orderLines.add(orderLine3_2);

        //Set order lines to order
        order1.setOrderLines(List.of(orderLine1_1, orderLine1_2));
        orders.add(order1);

        order2.setOrderLines(List.of(orderLine2_1));
        orders.add(order2);

        order3.setOrderLines(List.of(orderLine3_1, orderLine3_2));
        orders.add(order3);

        //Payments
        List<Payment> payments = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setPAYMENT_METHOD(PaymentMethod.MC);
        payment1.setPaymentDate(LocalDateTime.now());
        payment1.setAmount(order1Total);
        payments.add(payment1);

        Payment payment2 = new Payment();
        payment2.setPAYMENT_METHOD(PaymentMethod.VISA);
        payment2.setPaymentDate(LocalDateTime.now());
        payment2.setAmount(order2Total);
        payments.add(payment2);

        Payment payment3 = new Payment();
        payment3.setPAYMENT_METHOD(PaymentMethod.BANK_TRANSFER);
        payment3.setPaymentDate(LocalDateTime.now());
        payment3.setAmount(order3Total);
        payments.add(payment3);

        //Set payment to order
        order1.setPayment(payment1);
        order2.setPayment(payment2);
        order3.setPayment(payment3);

        categoryRepository.saveAll(categories);
        itemRepository.saveAll(items);
        userRepository.saveAll(users);
        paymentRepository.saveAll(payments);
        orderRepository.saveAll(orders);
        orderLineRepository.saveAll(orderLines);
    }
    Double calculateLineTotal(Integer qte, Double price) {
        return price * (double) qte;
    }
}

