create table season_tickets_types (
    id bigserial primary key,
    name varchar not null,
    price decimal not null
);

--rollback drop table season_tickets_types;