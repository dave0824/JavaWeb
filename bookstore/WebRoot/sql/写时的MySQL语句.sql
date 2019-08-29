select *from t_city;
select * from t_province
-- alter TABLE t_city change name cname varchar(20);
-- alter TABLE t_province change name pname varchar(20);
-- select * from t_city c,t_province p where c.pid = p.pid; 
use bookstore ;
show tables;

select* from tb_user;
select * from book;
select * from orders;
select * from orderitem
;SELECT * FROM orderitem i,book b WHERE i.bid=b.bid and oid='A13EE554C7DE4B6E94E14F4D5EFEFCB1' ;
SELECT * FROM orders WHERE uid='34C5D0A827DA429BA2E989FF5DA2A5A5' order by ordertime;
SELECT * FROM orders WHERE oid=1;
delete from book where bid='E38106AB228247F5BDEE8D8D793610BC';

update ORDER set del='0';
alter table book modify column del boolean;
desc book;
mysqldump -uroot -p bookstore > f:/bookstore.sql
create table ad_user(
	'aid' char(32) not null,
    'aname' varchar(100),
    'password' varchar(100),
    'state' smallint(1) 
	PRIMARY KEY (`aid`)
);

CREATE TABLE `admin` (
  `aid` char(32) NOT NULL,
  `aname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`aid`)
);
create table aa(
	aa char(10) primary key,
    bb varchar(100)
);
drop table ad_user;
select * from admin;