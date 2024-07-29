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

create table cd.members (
    memid int primary key,
    firstname varchar(200),
    surname varchar(200)
);

insert into cd.members (memid, firstname, surname) values
(1, 'firstname 1', 'surname 1'),
(2, 'firstname 2', 'surname 2'),
(3, 'firstname 3', 'surname 3');


select name, rank
from (select facs.name as name,row_number() over (order by sum(case
            when memid = 0 then slots * facs.guestcost
            else slots * membercost
        end) desc) as rank
    from cd.bookings bks
    inner join cd.facilities facs
        on bks.facid = facs.facid
    group by facs.name
) as subq
where rank <= 3
order by rank