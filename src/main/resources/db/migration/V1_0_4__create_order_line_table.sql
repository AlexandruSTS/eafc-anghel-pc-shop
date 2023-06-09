CREATE TABLE `ORDER_LINE` (
                             ID INT PRIMARY KEY AUTO_INCREMENT,
                             ORDER_ID INT,
                             ITEM_ID INT,
                             QUANTITY INT,
                             CONSTRAINT FK_ORDER_LINE_ORDERS
                                 FOREIGN KEY (ORDER_ID) REFERENCES `ORDERS`(ID),
                             CONSTRAINT FK_ORDER_LINE_ITEMS
                                 FOREIGN KEY (ITEM_ID) REFERENCES `ITEM`(ID)
);