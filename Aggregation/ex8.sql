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

select facid, sum(slots) as "Total Slots" from cd.bookings
group by facid
having sum(slots) > 1000
order by facid