create schema if not exists cd;

create table cd.members (
    memid int primary key,
    firstname varchar(200),
    surname varchar(200),
    recommendedby int,
    foreign key (recommendedby) references cd.members(memid)
);

insert into cd.members (memid, firstname, surname, recommendedby) values
(1, 'John', 'Smith', null),
(2, 'Robert', 'Johnson', 1),
(3, 'Michael', 'Williams', 1),
(4, 'James', 'Brown', 2);

create table cd.facilities (
    facid int primary key,
    name varchar(100)
);

insert into cd.facilities (facid, name) values
(1, 'Tennis Court 1'),
(2, 'Tennis Court 2');

create table cd.bookings (
    bookid int primary key,
    facid int,
    memid int,
    starttime timestamp,
    foreign key (facid) references cd.facilities(facid),
    foreign key (memid) references cd.members(memid)
);

insert into cd.bookings (bookid, facid, memid, starttime) values
(1, 1, 1, '2012-09-21 10:00:00'),
(2, 2, 2, '2012-09-21 13:00:00'),
(3, 1, 3, '2012-09-21 15:00:00');

select distinct mems.firstname || ' ' || mems.surname as member, facs.name as facility from cd.members mems
inner join cd.bookings bks
    on mems.memid = bks.memid
inner join cd.facilities facs
    on bks.facid = facs.facid
where facs.name in ('Tennis Court 2', 'Tennis Court 1')
order by member, facility;
