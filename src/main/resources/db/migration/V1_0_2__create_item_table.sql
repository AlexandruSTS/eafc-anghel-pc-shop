CREATE TABLE ITEM (
                      ID SERIAL PRIMARY KEY,
                      NAME VARCHAR(255) NOT NULL,
                      DESCRIPTION VARCHAR(255),
                      PRICE DOUBLE PRECISION NOT NULL
);