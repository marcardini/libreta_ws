--drop database libreta_digital

--create database libreta_digital

--use libreta_digital

insert into institution values (1,'Elbio Fernandez')
go
insert into notebook values (1, 2016, 'normal', 'ABC', null, null)
go
insert into subject values (1, 'MATEMATICAS', null, 0)
go
update notebook set subject_oid = 1 where oid = 1
go
insert into group_ values (1,'1A',2016,null,null,1)
go
update notebook set group_oid = 1 where oid = 1
go
insert into professor values (1, 'maria', 'tarigo', '1990-06-20 00:00:00', 'FEMALE', 'maria.tarigo@gmail.com', 'admin', 5, '2016-01-01 00:00:00', 1, 0, 1)
go
insert into course values (1,'quinto',1,0,1,0)
go
update group_ set course_id = 1 where oid = 1
go
update subject set course_id = 1 where oid = 1
go
insert into student values (1,'matias', 'hernandez', '1989-01-01 00:00:00', 'MALE', 'mati@gmail.com', true, 1, null, 1)
go
insert into student values (2,'mathias', 'arcardini', '1989-05-17 00:00:00', 'MALE', 'arcardinimathias@gmail.com', true, 1, null, 1)
go
insert into student values (3,'laura', 'pereira', '1993-02-08 00:00:00', 'FEMALE', 'laura@gmail.com', true, 1, null, 1)
go
insert into student values (4,'jorge', 'berro', '1974-11-30 00:00:00', 'MALE', 'berro@gmail.com', true, 1, null, 1)
go
insert into student values (5,'vanesa', 'perez', '1954-03-20 00:00:00', 'FEMALE', 'vaneperez@gmail.com', true, 1, null, 1)
