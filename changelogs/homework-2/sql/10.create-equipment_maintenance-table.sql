create table equipment_maintenance (
    id bigserial primary key,
    equipment_id bigint not null,
    maintenance_date date not null,
    description text,
    foreign key (equipment_id) references equipment(id)
);

--rollback drop table equipment_maintenance;