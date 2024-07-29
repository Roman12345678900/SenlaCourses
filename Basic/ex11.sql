CREATE SCHEMA IF NOT EXISTS cd;

CREATE TABLE cd.members (
    memid INT PRIMARY KEY,
    surname VARCHAR(200),
    firstname VARCHAR(200),
    joindate DATE
);

INSERT INTO cd.members (memid, surname, firstname, joindate) VALUES
(1, 'Smith', 'John', '2012-08-01'),
(2, 'Johnson', 'Robert', '2012-09-01'),
(3, 'Williams', 'Michael', '2012-10-01');

select max(joindate) as latest from cd.members