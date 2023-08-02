CREATE SCHEMA IF NOT EXISTS local;

DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Taco;
DROP TABLE IF EXISTS Taco_Order;
DROP TABLE IF EXISTS Users;

CREATE TABLE Ingredient (
    id   SERIAL,
    slug VARCHAR(4)  NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE Taco (
    id             SERIAL,
    name           VARCHAR(50) NOT NULL,
    ingredient_ids INTEGER[]
);

CREATE TABLE Taco_Order (
    id              SERIAL,
    delivery_name   VARCHAR(50) NOT NULL,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city   VARCHAR(50) NOT NULL,
    delivery_state  VARCHAR(2)  NOT NULL,
    delivery_zip    VARCHAR(10) NOT NULL,
    cc_number       VARCHAR(16) NOT NULL,
    cc_expiration   VARCHAR(5)  NOT NULL,
    cc_cvv          VARCHAR(3)  NOT NULL,
    taco_ids        INTEGER[]
);

create table Users (
    id           SERIAL,
    city         varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    state        varchar(255),
    street       varchar(255),
    username     varchar(255),
    zip          varchar(255),
    user_role    varchar(255)
);