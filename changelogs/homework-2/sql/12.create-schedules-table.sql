create table schedules (
    id bigserial primary key,
    status varchar not null,
    type varchar not null,
    start_time date not null,
    end_time date not null,
    schedule_id bigint,
    class_id bigint not null,
    foreign key (schedule_id) references schedules(id),
    foreign key (class_id) references classes(id)
);

--rollback drop table schedules;