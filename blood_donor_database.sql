-- create database blood_donor_databse;
use blood_donor_databse;
--   create table donor_table(
-- mobile varchar(20),
-- image VARCHAR(200),
-- name varchar(50),
-- gender varchar(10),
-- address varchar(200),
-- city varchar(50),
-- bgroup varchar(50),
-- age int,
-- disease varchar(50),
-- date varchar(50),

-- PRIMARY KEY(mobile)


-- );
-- create table donations(
-- mobile varchar(50),
-- bgroup varchar(50),
-- dateofdonation date
-- );
-- create table total_blood_record(
-- bgroup varchar(50),
-- count int default 0
-- );
-- insert into total_blood_record (bgroup,count) values
-- ('A+',0);
-- insert into total_blood_record (bgroup,count) values
-- ('B',0);
-- insert into total_blood_record (bgroup,count) values
-- ('B+',0);
-- insert into total_blood_record (bgroup,count) values
-- ('AB',0);
-- insert into total_blood_record (bgroup,count) values
-- ('AB+',0);
-- insert into total_blood_record (bgroup,count) values
-- ('A',0);

-- create table total_record(
-- Ap int,
-- An int,
-- Bp int,
-- Bn int,
-- ABp int,
-- ABn int

-- );
-- insert into total_record (Ap,An,Bp,Bn,ABp,ABn) values
-- (0,0,0,0,0,0);

-- create table issued(
-- name varchar(50),
-- mobile varchar(50),
-- hospital varchar(50),
-- reason varchar(50),
-- issuedate date
-- );
ALTER TABLE issued
ADD bgroup varchar(50);
select * from donations;
select * from total_record;
select * from donor_table;
select * from issued;
update issued set bgroup="A+" where mobile="1234567890";