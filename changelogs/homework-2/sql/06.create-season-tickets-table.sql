create table season_tickets (
    id bigserial primary key,
    user_id bigint not null,
    start_date date not null,
    end_date date not null,
    season_tickets_types_id bigint not null,
    foreign key (user_id) references users(id),
    foreign key (season_tickets_types_id) references season_tickets_types(id)
);

--rollback drop table season_tickets;