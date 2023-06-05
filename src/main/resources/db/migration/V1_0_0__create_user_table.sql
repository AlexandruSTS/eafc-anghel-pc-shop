CREATE TABLE _USER
(
    ID            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    FIRST_NAME    VARCHAR(255)                   NOT NULL,
    LAST_NAME     VARCHAR(255)                   NOT NULL,
    EMAIL         VARCHAR(255)                   NOT NULL,
    PASSWORD      VARCHAR(255)                   NOT NULL,
    DATE_OF_BIRTH TIMESTAMP                      NOT NULL,
    USER_ROLE     VARCHAR(255)                   NOT NULL
);