create table equipment (
    id bigserial primary key,
    name varchar not null,
    description text,
    purchase_date date,
    status varchar
);

--rollback drop table equipment;