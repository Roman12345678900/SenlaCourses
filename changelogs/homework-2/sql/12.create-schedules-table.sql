create table schedules (
    id bigserial primary key not null,
    status varchar(40) not null,
    type varchar(20) not null,
    start_time timestamp not null,
    end_time timestamp not null,
    schedule_id bigint not null,
    class_id bigint not null,
    foreign key (schedule_id) references schedules(id),
    foreign key (class_id) references classes(id)
);

--rollback drop table schedules;