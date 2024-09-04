create table profiles (
    id bigserial primary key not null,
    user_id bigint unique not null,
    address varchar(100) default null,
    date_of_birth date default null,
    gender varchar(30) default null,
    phone varchar(20) default null,
    foreign key (user_id) references users(id)
);

--rollback drop table user_profiles