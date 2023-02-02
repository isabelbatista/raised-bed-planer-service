--liquibase formatted sql

--changeset IsabelBatista:1
create table raised_beds (
    id serial primary key,
    name varchar(255) NOT NULL,
    dimension_x int NOT NULL,
    dimension_y int NOT NULL,
    default_field_count_x int NOT NULL,
    default_field_count_y int NOT NULL
);
--rollback drop table raised_beds;

--changeset IsabelBatista:2
create table plantings (
    id serial primary key,
    status varchar(60) NOT NULL,
    year int,
    field_count_x int NOT NULL,
    field_count_y int NOT NULL
);
--rollback drop table plantings;

--changeset IsabelBatista:3
create table plants (
    id serial primary key,
    name varchar(255) NOT NULL,
    description varchar(255)
);
--rollback drop table plants;


