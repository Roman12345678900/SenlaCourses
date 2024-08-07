create table classes (
    id bigserial primary key,
    name varchar not null,
    description text,
    user_id bigint not null,
    foreign key (user_id) references users(id)
);

--rollback drop table classes;