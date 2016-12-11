--drop database libreta_digital

--create database libreta_digital

--use libreta_digital


insert into institution values (1,'Elbio Fernandez')
go
insert into notebook values (1, 2016, 'NORMAL', 'ABC', null, null, null)
go
insert into subject values (1, 'MATEMATICAS', null, 0)
go
update notebook set subject_oid = 1 where oid = 1
go
insert into group_ values (1,'1A',2016,null,0)
go
update notebook set group_id = 1 where oid = 1
go
insert into professor values (1, 'maria', 'tarigo', '1990-06-20 00:00:00', 'FEMALE', 'maria.tarigo@gmail.com', 'admin', 'GRADE_5', '2016-01-01 00:00:00', null, 1, 0)
go
insert into course values (1,'primero',1,0)
go
update group_ set professor_id = 1 where oid = 1
go
update subject set group_id = 1 where oid = 1
go
update notebook set professor_id = 1 where oid = 1
go
insert into student values (1,'matias', 'hernandez', '1989-01-01 00:00:00', 'MALE', 'mati@gmail.com', true, 1, 0, 1)
go
insert into student values (2,'mathias', 'arcardini', '1989-05-17 00:00:00', 'MALE', 'arcardinimathias@gmail.com', true, 1, 1, 1)
go
insert into student values (3,'laura', 'pereira', '1993-02-08 00:00:00', 'FEMALE', 'laura@gmail.com', true, 1, 2, 1)
go
insert into student values (4,'jorge', 'berro', '1974-11-30 00:00:00', 'MALE', 'berro@gmail.com', true, 1, 3, 1)
go
insert into student values (5,'vanesa', 'perez', '1954-03-20 00:00:00', 'FEMALE', 'vaneperez@gmail.com', true, 1, 4, 1)
go
insert into class_day_student values(1,'2016-11-24 00:00:00', 'FALTA', 1,1,1,1,null,null)
go
insert into class_day_student values(2,'2016-11-25 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null)
go
insert into class_day_student values(3,'2016-11-24 00:00:00', 'FALTA', 1,1,1,1,null,null)
go
insert into class_day_student values(4,'2016-11-25 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(5,'2016-11-24 00:00:00', 'FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(6,'2016-11-25 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(7,'2016-11-24 00:00:00', 'FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(8,'2016-11-25 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(9,'2016-11-24 00:00:00', 'FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(10,'2016-11-25 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null);
go
insert into class_day_student values(11,'2016-11-25 00:00:00', 'EXAMEN', 1,1,1,1,6,'pobre')
go
insert into class_day_student values(12,'2016-11-25 00:00:00', 'EXAMEN', 1,1,1,1,7,'pobre')
go
insert into class_day_student values(13,'2016-11-25 00:00:00', 'EXAMEN', 1,1,1,1,8,'pobre')
go
insert into class_day_student values(14,'2016-11-25 00:00:00', 'EXAMEN', 1,1,1,1,9,'bien')
go
insert into class_day_student values(15,'2016-11-25 00:00:00', 'EXAMEN', 1,1,1,1,10,'excelente')
go
insert into roles values(1, 'ADMIN')
go
insert into permissions values(1, 'ASSISTANCE_CONTROL')
go
insert into permissions values(2, 'QUALIFY')
go
insert into permissions values(3, 'MASIVE_CHARGE')
go
insert into permissions values(4, 'STUDENT_FILE')
go
insert into privileges values(1,1,'WRITE',1,0)
go
insert into privileges values(2,2,'WRITE',1,1)
go
insert into privileges values(3,3,'WRITE',1,2)
go
insert into privileges values(4,4,'WRITE',1,3)
go
update professor set roleId = 1 where oid = 1