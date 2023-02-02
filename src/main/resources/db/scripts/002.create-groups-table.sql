--liquibase formatted sql

--changeset IsabelBatista:2
create table plant_groups (
    id serial primary key,
    name varchar(255) NOT NULL
);
--rollback drop table plant_groups;


