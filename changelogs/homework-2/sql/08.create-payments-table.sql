create table payments (
    id bigserial primary key,
    user_id bigint not null,
    payment_date date not null,
    price decimal not null,
    foreign key (user_id) references users(id)
);

--rollback drop table payments;