CREATE TABLE _USER
(
    ID            INT PRIMARY KEY NOT NULL,
    FIRST_NAME    VARCHAR(255)    NOT NULL,
    LAST_NAME     VARCHAR(255)    NOT NULL,
    EMAIL         VARCHAR(255)    NOT NULL,
    PASSWORD      VARCHAR(255)    NOT NULL,
    DATE_OF_BIRTH DATETIME        NOT NULL,
    USER_ROLE_ID INT NOT NULL ,
    constraint FK_USER_ROLE_ID
        foreign key (USER_ROLE_ID) references
            ROLE (ID)
);