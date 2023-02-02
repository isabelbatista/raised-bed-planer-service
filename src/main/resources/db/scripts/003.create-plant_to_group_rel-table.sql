--liquibase formatted sql

--changeset IsabelBatista:3
create table plant_to_group_rel (
    plant_group_id Integer NOT NULL,
    plant_group_name varchar(255),
    plant_id Integer NOT NULL,
    plant_name varchar(255)
);
--rollback drop table plant_to_group_rel;


