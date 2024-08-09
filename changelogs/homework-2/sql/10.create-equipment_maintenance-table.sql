create table equipment_maintenance (
    id bigserial primary key not null,
    equipment_id bigint not null,
    maintenance_date date not null,
    description varchar(500) not null,
    foreign key (equipment_id) references equipment(id)
);

--rollback drop table equipment_maintenance;