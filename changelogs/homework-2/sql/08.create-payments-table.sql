create table payments (
    id bigserial primary key not null,
    user_id bigint not null,
    payment_date timestamp not null,
    price decimal(6,2) not null,
    foreign key (user_id) references users(id)
);

--rollback drop table payments;