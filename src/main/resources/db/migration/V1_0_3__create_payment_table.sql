CREATE TABLE PAYMENT (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         PAYMENT_METHOD VARCHAR(255),
                         PAYMENT_DATE TIMESTAMP NOT NULL,
                         AMOUNT DOUBLE
);