create schema if not exists cd;

create table cd.members (
    memid int primary key,
    firstname varchar(100),
    surname varchar(100),
    recommendedby int,
    foreign key (recommendedby) references cd.members(memid)
);

insert into cd.members (memid, firstname, surname, recommendedby) values
(1, 'john', 'smith', null),
(2, 'robert', 'johnson', 1),
(3, 'michael', 'williams', 1),
(4, 'james', 'brown', 2);

select recommendedby, count(*) from cd.members
    where recommendedby is not null
    group by recommendedby
    order by recommendedby
