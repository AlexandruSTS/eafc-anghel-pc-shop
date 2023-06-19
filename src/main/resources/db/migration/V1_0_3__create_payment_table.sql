CREATE TABLE PAYMENT (
                         ID SERIAL PRIMARY KEY,
                         PAYMENT_METHOD VARCHAR(255),
                         PAYMENT_DATE TIMESTAMP NOT NULL,
                         AMOUNT DOUBLE PRECISION
);