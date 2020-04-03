CREATE DATABASE jdbc_demo CHARSET UTF8;
USE jdbc_demo;
CREATE TABLE student
(
    id            VARCHAR(10) PRIMARY KEY,
    name          VARCHAR(10) NOT NULL,
    gender        ENUM ('男', '女'),
    entrance_year YEAR        NOT NULL,
    birthday      DATE        NOT NULL,
    class         VARCHAR(10) NOT NULL
);