create table trainer_schedules (
    id bigserial primary key,
    user_id bigint not null,
    start_time date not null,
    end_time date not null,
    foreign key (user_id) references users(id)
);

--rollback drop table trainer_schedules;