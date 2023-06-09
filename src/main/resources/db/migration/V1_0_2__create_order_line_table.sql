CREATE TABLE `ORDER_LINE` (
                             ID INT PRIMARY KEY AUTO_INCREMENT,
                             ORDER_ID INT,
                             QUANTITY INT,
                             CONSTRAINT FK_ORDER_LINE_ORDERS
                                 FOREIGN KEY (ORDER_ID) REFERENCES `ORDERS`(ID)
);