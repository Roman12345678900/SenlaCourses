create table user_profiles (
    id bigserial primary key,
    user_id bigint unique not null,
    address varchar,
    date_of_birth date,
    gender varchar,
    phone varchar,
    foreign key (user_id) references users(id)
);

--rollback drop table user_profiles