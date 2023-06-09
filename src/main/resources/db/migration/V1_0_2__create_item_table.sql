CREATE TABLE ITEM (
                      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                      NAME VARCHAR(255) NOT NULL,
                      DESCRIPTION VARCHAR(255),
                      PRICE DECIMAL(10, 2) NOT NULL
);