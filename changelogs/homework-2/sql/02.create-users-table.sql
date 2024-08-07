create table users (
    id bigserial primary key not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(50) not null
);

--rollback drop table users