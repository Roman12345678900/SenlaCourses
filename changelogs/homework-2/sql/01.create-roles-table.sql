create table roles (
    id bigserial primary key,
    name varchar not null
);

--rollback drop table roles;