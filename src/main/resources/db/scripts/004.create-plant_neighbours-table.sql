--liquibase formatted sql

--changeset IsabelBatista:4
create table plant_neighbours (
    plant_one_id Integer NOT NULL,
    plant_one_name varchar(255),
    plant_two_id Integer,
    plant_two_name varchar(255),
    group_id Integer,
    group_name varchar (255),
    good_neighbour Boolean,
    bad_neighbour Boolean,
    comment varchar(255)
);
--rollback drop table plant_neighbours;



