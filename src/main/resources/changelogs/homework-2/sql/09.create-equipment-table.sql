create table equipment (
    id bigserial primary key,
    name varchar(40) not null,
    description varchar(500) not null,
    purchase_date timestamp not null,
    status varchar(30) not null
);

--rollback drop table equipment;