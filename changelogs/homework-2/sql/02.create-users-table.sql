create table users (
    id bigserial primary key,
    first_name varchar not null,
    last_name varchar,
    email varchar not null
);

--rollback drop table users