create table trainer_schedules (
    id bigserial primary key not null,
    user_id bigint not null,
    start_time timestamp not null,
    end_time timestamp not null,
    foreign key (user_id) references users(id)
);

--rollback drop table trainer_schedules;