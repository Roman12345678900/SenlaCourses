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

select 
    mems.firstname as memfname, 
    mems.surname as memsname, 
    (select recs.firstname 
     from cd.members recs 
     where recs.memid = mems.recommendedby) as recfname, 
    (select recs.surname 
     from cd.members recs 
     where recs.memid = mems.recommendedby) as recsname
from 
    cd.members mems
order by 
    memsname, memfname;