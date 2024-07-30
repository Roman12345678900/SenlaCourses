create schema if not exists cd;

create table cd.members (
    memid INT PRIMARY KEY,
    firstname VARCHAR(200),
    surname VARCHAR(200),
    recommendedby INT,
    FOREIGN KEY (recommendedby) references cd.members(memid)
);

insert into cd.members (memid, firstname, surname, recommendedby) values
(1, 'John', 'Smith', null),
(2, 'Robert', 'Johnson', 1),
(3, 'Michael', 'Williams', 1),
(4, 'James', 'Brown', 2);

select distinct recs.firstname as firstname, recs.surname as surname from cd.members mems
		inner join cd.members recs
		on recs.memid = mems.recommendedby
order by surname, firstname
