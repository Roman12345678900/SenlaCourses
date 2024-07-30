create schema if not exists cd;

create table cd.bookings (
    facid int primary key,
    memid int,
    starttime timestamp,
    slots int
);

insert into cd.bookings (facid, memid, starttime, slots) values
(1, 1, '2012-09-14 10:00:00', 2),
(2, 2, '2012-09-14 13:00:00', 3),
(3, 3, '2012-09-14 15:00:00', 1);


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


select facid, sum(slots) as "Total Slots" from cd.bookings
group by facid
order by sum(slots) desc
limit 1

