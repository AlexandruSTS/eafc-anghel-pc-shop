CREATE TABLE ORDER_LINE (
                            ID SERIAL PRIMARY KEY,
                            ORDER_ID INTEGER,
                            ITEM_ID INTEGER,
                            QUANTITY INTEGER,
                            CONSTRAINT FK_ORDER_LINE_ORDERS
                                FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID),
                            CONSTRAINT FK_ORDER_LINE_ITEMS
                                FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ID)
);