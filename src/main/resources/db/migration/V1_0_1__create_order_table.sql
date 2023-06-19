CREATE TABLE ORDERS (
                        ID SERIAL PRIMARY KEY,
                        CUSTOMER_ID INTEGER NOT NULL,
                        ORDER_DATE TIMESTAMP NOT NULL,
                        TOTAL_AMOUNT DOUBLE PRECISION,
                        STATUS VARCHAR(255),
                        SHIPPING_ADDRESS VARCHAR(255),
                        PAYMENT_ID INTEGER,
                        CONSTRAINT FK_ORDER_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) REFERENCES _USER (ID)
);