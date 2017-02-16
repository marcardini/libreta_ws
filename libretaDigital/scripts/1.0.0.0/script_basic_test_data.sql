--drop database libreta_digital

--create database libreta_digital

--use libreta_digital


insert into institution values (1,'Elbio Fernandez')
go
insert into notebook values (1, 2017, 'NORMAL', 'ABC', null, null, null,  null, null, null, null)
go
insert into subject values (1, 'MATEMATICAS', null, 0)
go
INSERT INTO `libreta_digital`.`subject` (`oid`, `name`, `group_id`, `index_subjects`) VALUES ('3', 'HISTORIA', '1', '0')
GO
INSERT INTO `libreta_digital`.`subject` (`oid`, `name`, `group_id`, `index_subjects`) VALUES ('3', 'FISICA', '1', '0')
go
INSERT INTO `libreta_digital`.`subject` (`oid`, `name`, `group_id`, `index_subjects`) VALUES ('4', 'QUIMICA', '1', '0')
go
INSERT INTO `libreta_digital`.`subject` (`oid`, `name`, `group_id`, `index_subjects`) VALUES ('5', 'LITERATURA', '1', '0')
go
update notebook set subject_oid = 1 where oid = 1
go
insert into group_ values (1,'1A',2017,null,0)
go
update notebook set group_id = 1 where oid = 1
go
insert into professor values (1, 'ADMIN', '-', '1990-06-20 00:00:00', 'PENDING', 'admin', 'admin', '-', '2017-01-01 00:00:00', null, 1, 0, '094555996', 'MATEMATICAS')
go
INSERT INTO `libreta_digital`.`professor` VALUES ('2', 'juan', 'lopez', '1978-02-06', 'MALE', 'mail2@mail', 'admin', 'GRADE_5', '2016-01-01', '1234568', '1', '0', '0', 'HISTORIA')
go
insert into course values (1,'primero',1,0)
go
update group_ set professor_id = 1 where oid = 1
go
update subject set group_id = 1 where oid = 1
go
update notebook set professor_id = 1 where oid = 1
go
insert into student values (1,'matias', 'hernandez', '1989-01-01 00:00:00', 'MALE', 'mati@gmail.com', true, 1, 0, 1, '098563741')
go
insert into student values (2,'mathias', 'arcardini', '1989-05-17 00:00:00', 'MALE', 'arcardinimathias@gmail.com', true, 1, 1, 1, '091377334')
go
insert into student values (3,'laura', 'pereira', '1993-02-08 00:00:00', 'FEMALE', 'laura@gmail.com', true, 1, 2, 1, '099653455')
go
insert into student values (4,'jorge', 'berro', '1974-11-30 00:00:00', 'MALE', 'berro@gmail.com', true, 1, 3, 1, '091586483')
go
insert into student values (5,'vanesa', 'perez', '1954-03-20 00:00:00', 'FEMALE', 'vaneperez@gmail.com', true, 1, 4, 1, '097563783')
go
insert into class_day_student values(1,'2017-02-06 00:00:00', 'FALTA', 1,1,1,1,null,null)
go
insert into class_day_student values(2,'2017-02-07 00:00:00', 'MEDIA_FALTA', 1,1,1,1,null,null)
go
insert into class_day_student values(3,'2017-02-06 00:00:00', 'FALTA', 2,1,1,1,null,null)
go
insert into class_day_student values(4,'2017-02-07 00:00:00', 'MEDIA_FALTA', 2,1,1,1,null,null);
go
insert into class_day_student values(5,'2017-02-06 00:00:00', 'FALTA', 3,1,1,1,null,null);
go
insert into class_day_student values(6,'2017-02-07 00:00:00', 'MEDIA_FALTA', 3,1,1,1,null,null);
go
insert into class_day_student values(11,'2017-02-06 00:00:00', 'EXAMEN', 1,1,1,1,6,'pobre')
go
insert into class_day_student values(12,'2017-02-07 00:00:00', 'PARCIAL', 1,1,1,1,7,'pobre')
go
insert into class_day_student values(13,'2017-02-06 00:00:00', 'PROMEDIO_TEORICO', 1,1,1,1,8,'pobre')
go
insert into class_day_student values(14,'2017-02-07 00:00:00', 'PRIMER_PROMEDIO', 1,1,1,1,9,'bien')
go
insert into class_day_student values(15,'2017-02-06 00:00:00', 'PRIMER_EVALUACIÓN_ESPECIAL', 1,1,1,1,10,'excelente')
go
INSERT INTO `libreta_digital`.`class_day_professor` (`oid`, `class_date`, `notebook_id`, `comment`) VALUES ('1', '2017-02-14', '1', 'Nota del curso')
go
INSERT INTO `libreta_digital`.`class_day_professor` (`oid`, `class_date`, `notebook_id`, `comment`) VALUES ('2', '2017-02-15', '1', 'Otra Nota')
go
INSERT INTO `libreta_digital`.`class_day_professor` (`oid`, `class_date`, `notebook_id`, `comment`) VALUES ('3', '2017-02-16', '1', 'La ultima')
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
go
insert into bulletin values (1, 1, '2017-03-01 00:00:00', '2017-06-30 23:59:59', 1, 10, 3, 'mejoro la conducta', false)