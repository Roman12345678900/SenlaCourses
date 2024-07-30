create schema if not exists cd;

create table cd.facilities (
    facid int primary key,
    name varchar(100),
    membercost numeric,
    guestcost numeric
);

insert into cd.facilities (facid, name, membercost, guestcost) values
(1, 'tennis court 1', 5.00, 10.00),
(2, 'tennis court 2', 2.50, 5.00),
(3, 'swimming pool', 2.50, 5.00),
(4, 'gym', 1.00, 2.00);

select count(*) from cd.facilities where guestcost >= 10
