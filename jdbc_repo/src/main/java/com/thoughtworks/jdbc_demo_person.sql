USE jdbc_demo;
CREATE TABLE person
(
    id     VARCHAR(10) PRIMARY KEY,
    name   VARCHAR(10) NOT NULL,
    gender ENUM ('男', '女')
);