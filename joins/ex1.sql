create schema if not exists cd;

create table cd.members (
    memid int primary key,
    surname varchar(200),
    firstname varchar(200),
    joindate date
);

insert into cd.members (memid, surname, firstname, joindate) values
(1, 'Smith', 'John', '2012-08-01'),
(2, 'Johnson', 'Robert', '2012-09-01'),
(3, 'Williams', 'Michael', '2012-10-01');


create schema if not exists cd;

create table cd.bookings(
    facid int primary key,
    memid int,
    starttime timestamp,
    slots int
);

insert into cd.bookings(facid, memid, starttime, slots) values
(3, 1, '2012-07-03 11:00:00', 2),
(4, 1, '2012-07-03 08:00:00', 2),
(6, 0, '2012-10-03 18:00:00', 2),
(7, 1, '2012-09-15 19:00:00', 1);

select bks.starttime from cd.bookings bks, cd.members mems
where mems.firstname = 'David'
      and mems.surname = 'Farrell'
      and mems.memid = bks.memid;