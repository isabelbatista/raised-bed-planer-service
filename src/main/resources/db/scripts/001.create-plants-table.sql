--liquibase formatted sql

--changeset IsabelBatista:1
create table plants (
    id serial primary key,
    name varchar(255) NOT NULL
);
--rollback drop table plants;


