create table season_tickets_types (
    id bigserial primary key not null,
    name varchar(50) not null,
    price decimal(6,2) not null
);

--rollback drop table season_tickets_types;