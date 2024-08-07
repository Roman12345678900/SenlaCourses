create table card_info (
    card_number bigint primary key,
    card_holder varchar not null,
    validity_period date not null,
    user_id bigint not null,
    foreign key (user_id) references users(id)
);

--rollback drop table card_info;