create table card_info (
    card_number bigint primary key not null,
    card_holder_name varchar(40) not null,
    validity_period_start date not null,
    validity_period_end date not null,
    user_id bigint unique not null,
    foreign key (user_id) references users(id)
);

--rollback drop table card_info;