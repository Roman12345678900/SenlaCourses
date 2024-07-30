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
(3, 'Gym', 1.00, 2.00, 500.00, 20.00),
(4, 'Swimming Pool', 5.00, 1.00, 500.00, 500.00);


select *from cd.facilities where facid in (1,5)