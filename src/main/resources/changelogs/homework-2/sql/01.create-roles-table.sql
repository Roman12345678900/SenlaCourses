create table roles (
    id bigserial primary key not null,
    name varchar(15) not null
);

--rollback drop table roles;