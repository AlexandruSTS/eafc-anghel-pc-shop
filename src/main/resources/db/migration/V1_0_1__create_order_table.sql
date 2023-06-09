CREATE TABLE `ORDERS` (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         CUSTOMER_ID BIGINT NOT NULL,
                         ORDER_DATE TIMESTAMP NOT NULL,
                         TOTAL_AMOUNT DECIMAL,
                         STATUS VARCHAR(255),
                         SHIPPING_ADDRESS VARCHAR(255),
                         PAYMENT_ID BIGINT,
                         CONSTRAINT FK_ORDER_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) REFERENCES `_USER` (ID)
);