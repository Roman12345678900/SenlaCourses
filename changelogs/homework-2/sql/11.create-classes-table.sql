create table classes (
    id bigserial primary key not null,
    name varchar not null,
    description varchar(500) not null,
    user_id bigint not null,
    foreign key (user_id) references users(id)
);

--rollback drop table classes;