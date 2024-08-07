create table profiles (
    id bigserial primary key not null,
    user_id bigint unique not null,
    address varchar(100),
    date_of_birth date,
    gender varchar(30),
    phone varchar(20),
    foreign key (user_id) references users(id)
);

--rollback drop table user_profiles