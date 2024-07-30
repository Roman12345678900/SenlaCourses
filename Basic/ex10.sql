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

CREATE SCHEMA IF NOT EXISTS cd;

CREATE TABLE cd.facilities (
    facid INT PRIMARY KEY,
    name VARCHAR(100),
    membercost NUMERIC,
    guestcost NUMERIC,
    initialoutlay NUMERIC,
    monthlymaintenance NUMERIC
);

INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) VALUES
(1, 'Tennis Court', 5.00, 10.00, 1000.00, 50.00),
(2, 'Swimming Pool', 2.50, 5.00, 800.00, 30.00),
(3, 'Gym', 1.00, 2.00, 500.00, 20.00);


select surname from cd.members
union
select name from cd.facilities; 