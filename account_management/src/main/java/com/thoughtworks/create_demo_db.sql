CREATE DATABASE account_management_demo;
USE account_management_demo;
CREATE TABLE account_info
(
    user_name    VARCHAR(20) PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL,
    e_mail       VARCHAR(50) NOT NULL
);
CREATE TABLE account_security
(
    user_name       VARCHAR(20) NOT NULL
        PRIMARY KEY,
    password_digest BINARY(32)  NOT NULL,
    password_salt   BINARY(32)  NOT NULL,
    retries_left    TINYINT     NOT NULL
);
