/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.3.0

Source Server         : oracle连接orcl
Source Server Version : 100200
Source Host           : 127.0.0.1:1521
Source Schema         : SCOTT

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2018-07-11 17:11:13
*/


-- ----------------------------
-- Table structure for ADMIN
-- ----------------------------
DROP TABLE "SCOTT"."ADMIN";
CREATE TABLE "SCOTT"."ADMIN" (
"ANO" NUMBER(20) NOT NULL ,
"AWORD" VARCHAR2(20 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of ADMIN
-- ----------------------------
INSERT INTO "SCOTT"."ADMIN" VALUES ('1', '1234');
INSERT INTO "SCOTT"."ADMIN" VALUES ('2', '123456');

-- ----------------------------
-- Table structure for CARD
-- ----------------------------
DROP TABLE "SCOTT"."CARD";
CREATE TABLE "SCOTT"."CARD" (
"CARD_ID" NUMBER(20) NOT NULL ,
"SNO" NUMBER(20) NOT NULL ,
"TNO" NUMBER(20) NOT NULL ,
"CNO" NUMBER(20) NOT NULL ,
"START_TIME" DATE NOT NULL ,
"END_TIME" DATE NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of CARD
-- ----------------------------
INSERT INTO "SCOTT"."CARD" VALUES ('1', '160101', '1', '2', TO_DATE('2018-07-03 16:04:32', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-07-04 16:04:37', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."CARD" VALUES ('2', '160201', '2', '4', TO_DATE('2018-07-05 16:05:04', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-07-06 16:05:07', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."CARD" VALUES ('461', '160101', '451', '451', TO_DATE('2018-07-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-07-02 14:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for CLASS
-- ----------------------------
DROP TABLE "SCOTT"."CLASS";
CREATE TABLE "SCOTT"."CLASS" (
"CLNO" NUMBER(20) NOT NULL ,
"DEPARTMENT" VARCHAR2(20 BYTE) NOT NULL ,
"TNO" NUMBER(20) NOT NULL ,
"SUM" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of CLASS
-- ----------------------------
INSERT INTO "SCOTT"."CLASS" VALUES ('1604', '会计工程', '4', '51');
INSERT INTO "SCOTT"."CLASS" VALUES ('1601', '信息工程', '1', '44');
INSERT INTO "SCOTT"."CLASS" VALUES ('1602', '电气工程', '2', '45');
INSERT INTO "SCOTT"."CLASS" VALUES ('1603', '软件工程', '3', '46');

-- ----------------------------
-- Table structure for COURSE
-- ----------------------------
DROP TABLE "SCOTT"."COURSE";
CREATE TABLE "SCOTT"."COURSE" (
"CNO" NUMBER(20) NOT NULL ,
"CNAME" VARCHAR2(20 BYTE) NOT NULL ,
"TNO" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of COURSE
-- ----------------------------
INSERT INTO "SCOTT"."COURSE" VALUES ('1', '计算机组成原理', '2');
INSERT INTO "SCOTT"."COURSE" VALUES ('2', '自动控制原理', '2');
INSERT INTO "SCOTT"."COURSE" VALUES ('3', '工程制图', '3');
INSERT INTO "SCOTT"."COURSE" VALUES ('4', 'c++语言程序设计', '4');
INSERT INTO "SCOTT"."COURSE" VALUES ('451', 'java语言程序设计', '451');

-- ----------------------------
-- Table structure for L_USER
-- ----------------------------
DROP TABLE "SCOTT"."L_USER";
CREATE TABLE "SCOTT"."L_USER" (
"USERNAME" VARCHAR2(20 BYTE) NOT NULL ,
"PASSWORD" VARCHAR2(20 BYTE) NOT NULL ,
"QXNO" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of L_USER
-- ----------------------------
INSERT INTO "SCOTT"."L_USER" VALUES ('04', '123', '2');
INSERT INTO "SCOTT"."L_USER" VALUES ('01', '1234', '0');
INSERT INTO "SCOTT"."L_USER" VALUES ('02', '12', '1');
INSERT INTO "SCOTT"."L_USER" VALUES ('03', 'L03', '2');

-- ----------------------------
-- Table structure for LEAVE
-- ----------------------------
DROP TABLE "SCOTT"."LEAVE";
CREATE TABLE "SCOTT"."LEAVE" (
"LON" NUMBER(20) NOT NULL ,
"SNO" NUMBER(20) NOT NULL ,
"CAUSE" VARCHAR2(20 BYTE) NOT NULL ,
"LTIME" DATE NOT NULL ,
"DAY" NUMBER(20) NOT NULL ,
"TNO" NUMBER(20) NOT NULL ,
"LSTATE" VARCHAR2(20 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of LEAVE
-- ----------------------------
INSERT INTO "SCOTT"."LEAVE" VALUES ('483', '160304', '回家结婚', TO_DATE('2018-07-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3', '451', '已批准');
INSERT INTO "SCOTT"."LEAVE" VALUES ('1', '160101', '生病', TO_DATE('2018-07-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '5', '1', '已批准');
INSERT INTO "SCOTT"."LEAVE" VALUES ('2', '160302', '请假', TO_DATE('2017-08-16 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '2', '2', '未批准');
INSERT INTO "SCOTT"."LEAVE" VALUES ('3', '160203', '丧假', TO_DATE('2017-08-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '1', '3', '已批准');

-- ----------------------------
-- Table structure for OUT
-- ----------------------------
DROP TABLE "SCOTT"."OUT";
CREATE TABLE "SCOTT"."OUT" (
"ONO" NUMBER(20) NOT NULL ,
"SNO" NUMBER(20) NOT NULL ,
"CNO" NUMBER(20) NOT NULL ,
"OTIME" DATE NOT NULL ,
"STATE" VARCHAR2(20 BYTE) NOT NULL ,
"TNO" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of OUT
-- ----------------------------
INSERT INTO "SCOTT"."OUT" VALUES ('451', '160304', '451', TO_DATE('2018-07-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '451');
INSERT INTO "SCOTT"."OUT" VALUES ('1', '160101', '1', TO_DATE('2017-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '1');
INSERT INTO "SCOTT"."OUT" VALUES ('2', '160201', '4', TO_DATE('2017-10-28 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '4');
INSERT INTO "SCOTT"."OUT" VALUES ('3', '160203', '3', TO_DATE('2017-09-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '未出勤', '3');
INSERT INTO "SCOTT"."OUT" VALUES ('4', '160302', '2', TO_DATE('2017-10-27 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '2');
INSERT INTO "SCOTT"."OUT" VALUES ('5', '160301', '4', TO_DATE('2017-10-16 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '未出勤', '4');
INSERT INTO "SCOTT"."OUT" VALUES ('6', '160102', '1', TO_DATE('2018-07-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '1');
INSERT INTO "SCOTT"."OUT" VALUES ('452', '160101', '451', TO_DATE('2018-07-11 16:24:37', 'YYYY-MM-DD HH24:MI:SS'), '出勤', '451');

-- ----------------------------
-- Table structure for QX
-- ----------------------------
DROP TABLE "SCOTT"."QX";
CREATE TABLE "SCOTT"."QX" (
"QXNO" NUMBER(20) NOT NULL ,
"QXNAME" VARCHAR2(20 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of QX
-- ----------------------------
INSERT INTO "SCOTT"."QX" VALUES ('0', '管理员');
INSERT INTO "SCOTT"."QX" VALUES ('1', '教师');
INSERT INTO "SCOTT"."QX" VALUES ('2', '学生');

-- ----------------------------
-- Table structure for SCORE
-- ----------------------------
DROP TABLE "SCOTT"."SCORE";
CREATE TABLE "SCOTT"."SCORE" (
"SNO" NUMBER(20) NOT NULL ,
"CNO" NUMBER(20) NOT NULL ,
"GRADE" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of SCORE
-- ----------------------------
INSERT INTO "SCOTT"."SCORE" VALUES ('160101', '1', '80');
INSERT INTO "SCOTT"."SCORE" VALUES ('160202', '2', '66');
INSERT INTO "SCOTT"."SCORE" VALUES ('160103', '1', '75');
INSERT INTO "SCOTT"."SCORE" VALUES ('160102', '4', '81');
INSERT INTO "SCOTT"."SCORE" VALUES ('160201', '3', '78');
INSERT INTO "SCOTT"."SCORE" VALUES ('160303', '2', '98');
INSERT INTO "SCOTT"."SCORE" VALUES ('160304', '1', '86');

-- ----------------------------
-- Table structure for STUDENT
-- ----------------------------
DROP TABLE "SCOTT"."STUDENT";
CREATE TABLE "SCOTT"."STUDENT" (
"SNO" NUMBER(20) NOT NULL ,
"SNAME" VARCHAR2(20 BYTE) NOT NULL ,
"SEX" VARCHAR2(20 BYTE) NOT NULL ,
"CLNO" NUMBER(20) NOT NULL ,
"BIRTH" DATE NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of STUDENT
-- ----------------------------
INSERT INTO "SCOTT"."STUDENT" VALUES ('160101', '刘春萍', '女', '1601', TO_DATE('1991-08-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160102', '李刚', '男', '1601', TO_DATE('2018-07-05 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160201', '张纯玉', '男', '1602', TO_DATE('1989-07-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160303', '张冬云', '女', '1603', TO_DATE('1989-12-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160202', '王天仪', '男', '1602', TO_DATE('1989-12-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160301', '赵风雨', '男', '1603', TO_DATE('1990-10-25 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160302', '张扬', '男', '1603', TO_DATE('1990-05-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160203', '高淼', '男', '1602', TO_DATE('1987-03-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160401', '李小龙', '男', '1604', TO_DATE('2018-07-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "SCOTT"."STUDENT" VALUES ('160304', '小曾', '男', '1603', TO_DATE('2018-07-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for TEACHER
-- ----------------------------
DROP TABLE "SCOTT"."TEACHER";
CREATE TABLE "SCOTT"."TEACHER" (
"TNO" NUMBER(20) NOT NULL ,
"TNAME" VARCHAR2(20 BYTE) NOT NULL ,
"CNO" NUMBER(20) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of TEACHER
-- ----------------------------
INSERT INTO "SCOTT"."TEACHER" VALUES ('1', '王彤', '2');
INSERT INTO "SCOTT"."TEACHER" VALUES ('2', '孔世杰', '3');
INSERT INTO "SCOTT"."TEACHER" VALUES ('3', '邹人文', '2');
INSERT INTO "SCOTT"."TEACHER" VALUES ('4', '韩冬梅', '1');
INSERT INTO "SCOTT"."TEACHER" VALUES ('5', '杨文化', '2');
INSERT INTO "SCOTT"."TEACHER" VALUES ('6', '崔天', '4');
INSERT INTO "SCOTT"."TEACHER" VALUES ('7', '孙晴碧', '2');
INSERT INTO "SCOTT"."TEACHER" VALUES ('8', '张珂', '3');
INSERT INTO "SCOTT"."TEACHER" VALUES ('451', '小曾', '2');

-- ----------------------------
-- Procedure structure for PRO_CLASSAVGSCORE
-- ----------------------------
CREATE OR REPLACE procedure "SCOTT"."PRO_CLASSAVGSCORE"(v_classid number,v_courseid number,v_avgscore out number)
as
begin
       declare
          v_classname varchar(50);
          v_coursename varchar(50);
       begin
          select classname into v_classname from class where classid=v_classid;
          select coursename into v_coursename from course where courseid=v_courseid;
          select avg(score) into v_avgscore from score where courseid=v_courseid and id in (select id from student where classid=v_classid);
          dbms_output.put_line(v_classname||'的'||v_coursename ||'平均分是:' ||v_avgscore );
       end;
end pro_ClassAvgScore;
/

-- ----------------------------
-- Procedure structure for PRO_STUDSCORE
-- ----------------------------
CREATE OR REPLACE procedure "SCOTT"."PRO_STUDSCORE"(studid in varchar2,studscore out number)
as
begin
       select sum(score) into studscore from score where id=studid;
       dbms_output.put_line('id='||studid||'的总分为:'||studscore);
end pro_studscore;
/

-- ----------------------------
-- Procedure structure for PRO_TEST
-- ----------------------------
CREATE OR REPLACE procedure "SCOTT"."PRO_TEST"
is
begin
       declare
          v_studname student.name%type;
          v_cardno student.cardno%type;
       begin
          select name,cardno into v_studname,v_cardno from student where id=1001;
          dbms_output.put_line('存储过程执行成功');
          dbms_output.put_line('id=1001的学生，name：'||v_studname||',cardno：'||v_cardno);
       end;
end pro_test;
/

-- ----------------------------
-- Function structure for FUN_STATICSSCORE
-- ----------------------------
CREATE OR REPLACE function "SCOTT"."FUN_STATICSSCORE"(p_studid score.id%TYPE)

return NUMBER

as
       total number(5,2);
begin
       select sum(score) into total from score where id=p_studid;
       return nvl(total,-1);
Exception
       when NO_DATA_FOUND THEN
            dbms_output.put_line('学号不存在1');
            return 0;
       when OTHERS then
            dbms_output.put_line('学号不存在2');
            return 0;
end fun_staticsScore;
/

-- ----------------------------
-- Sequence structure for CARD_ID
-- ----------------------------
DROP SEQUENCE "SCOTT"."CARD_ID";
CREATE SEQUENCE "SCOTT"."CARD_ID"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999
 START WITH 481
 CACHE 30;

-- ----------------------------
-- Sequence structure for CNO
-- ----------------------------
DROP SEQUENCE "SCOTT"."CNO";
CREATE SEQUENCE "SCOTT"."CNO"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999
 START WITH 481
 CACHE 30;

-- ----------------------------
-- Sequence structure for LON
-- ----------------------------
DROP SEQUENCE "SCOTT"."LON";
CREATE SEQUENCE "SCOTT"."LON"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999
 START WITH 511
 CACHE 30;

-- ----------------------------
-- Sequence structure for ONO
-- ----------------------------
DROP SEQUENCE "SCOTT"."ONO";
CREATE SEQUENCE "SCOTT"."ONO"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999
 START WITH 481
 CACHE 30;

-- ----------------------------
-- Sequence structure for TNO
-- ----------------------------
DROP SEQUENCE "SCOTT"."TNO";
CREATE SEQUENCE "SCOTT"."TNO"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999999999999
 START WITH 481
 CACHE 30;

-- ----------------------------
-- Indexes structure for table ADMIN
-- ----------------------------

-- ----------------------------
-- Checks structure for table ADMIN
-- ----------------------------
ALTER TABLE "SCOTT"."ADMIN" ADD CHECK ("AWORD" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ADMIN
-- ----------------------------
ALTER TABLE "SCOTT"."ADMIN" ADD PRIMARY KEY ("ANO");

-- ----------------------------
-- Indexes structure for table CARD
-- ----------------------------

-- ----------------------------
-- Checks structure for table CARD
-- ----------------------------
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("CARD_ID" IS NOT NULL);
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("SNO" IS NOT NULL);
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("TNO" IS NOT NULL);
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("CNO" IS NOT NULL);
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("START_TIME" IS NOT NULL);
ALTER TABLE "SCOTT"."CARD" ADD CHECK ("END_TIME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CARD
-- ----------------------------
ALTER TABLE "SCOTT"."CARD" ADD PRIMARY KEY ("CARD_ID");

-- ----------------------------
-- Indexes structure for table CLASS
-- ----------------------------

-- ----------------------------
-- Checks structure for table CLASS
-- ----------------------------
ALTER TABLE "SCOTT"."CLASS" ADD CHECK ("DEPARTMENT" IS NOT NULL);
ALTER TABLE "SCOTT"."CLASS" ADD CHECK ("TNO" IS NOT NULL);
ALTER TABLE "SCOTT"."CLASS" ADD CHECK ("SUM" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CLASS
-- ----------------------------
ALTER TABLE "SCOTT"."CLASS" ADD PRIMARY KEY ("CLNO");

-- ----------------------------
-- Indexes structure for table COURSE
-- ----------------------------

-- ----------------------------
-- Checks structure for table COURSE
-- ----------------------------
ALTER TABLE "SCOTT"."COURSE" ADD CHECK ("CNAME" IS NOT NULL);
ALTER TABLE "SCOTT"."COURSE" ADD CHECK ("TNO" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table COURSE
-- ----------------------------
ALTER TABLE "SCOTT"."COURSE" ADD PRIMARY KEY ("CNO");

-- ----------------------------
-- Indexes structure for table L_USER
-- ----------------------------

-- ----------------------------
-- Checks structure for table L_USER
-- ----------------------------
ALTER TABLE "SCOTT"."L_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "SCOTT"."L_USER" ADD CHECK ("QXNO" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table L_USER
-- ----------------------------
ALTER TABLE "SCOTT"."L_USER" ADD PRIMARY KEY ("USERNAME");

-- ----------------------------
-- Indexes structure for table LEAVE
-- ----------------------------

-- ----------------------------
-- Checks structure for table LEAVE
-- ----------------------------
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("SNO" IS NOT NULL);
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("CAUSE" IS NOT NULL);
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("LTIME" IS NOT NULL);
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("DAY" IS NOT NULL);
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("TNO" IS NOT NULL);
ALTER TABLE "SCOTT"."LEAVE" ADD CHECK ("LSTATE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table LEAVE
-- ----------------------------
ALTER TABLE "SCOTT"."LEAVE" ADD PRIMARY KEY ("LON");

-- ----------------------------
-- Indexes structure for table OUT
-- ----------------------------

-- ----------------------------
-- Checks structure for table OUT
-- ----------------------------
ALTER TABLE "SCOTT"."OUT" ADD CHECK ("SNO" IS NOT NULL);
ALTER TABLE "SCOTT"."OUT" ADD CHECK ("CNO" IS NOT NULL);
ALTER TABLE "SCOTT"."OUT" ADD CHECK ("OTIME" IS NOT NULL);
ALTER TABLE "SCOTT"."OUT" ADD CHECK ("STATE" IS NOT NULL);
ALTER TABLE "SCOTT"."OUT" ADD CHECK ("TNO" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table OUT
-- ----------------------------
ALTER TABLE "SCOTT"."OUT" ADD PRIMARY KEY ("ONO");

-- ----------------------------
-- Indexes structure for table QX
-- ----------------------------

-- ----------------------------
-- Checks structure for table QX
-- ----------------------------
ALTER TABLE "SCOTT"."QX" ADD CHECK ("QXNAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table QX
-- ----------------------------
ALTER TABLE "SCOTT"."QX" ADD PRIMARY KEY ("QXNO");

-- ----------------------------
-- Checks structure for table SCORE
-- ----------------------------
ALTER TABLE "SCOTT"."SCORE" ADD CHECK ("SNO" IS NOT NULL);
ALTER TABLE "SCOTT"."SCORE" ADD CHECK ("CNO" IS NOT NULL);
ALTER TABLE "SCOTT"."SCORE" ADD CHECK ("GRADE" IS NOT NULL);

-- ----------------------------
-- Indexes structure for table STUDENT
-- ----------------------------

-- ----------------------------
-- Triggers structure for table STUDENT
-- ----------------------------
CREATE OR REPLACE TRIGGER "SCOTT"."TEST_TRIGGER" AFTER INSERT OR DELETE OR UPDATE ON "SCOTT"."STUDENT" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW
begin
 update class set class.sum=class.sum-1 where class.clno= :old.clno;
 update class set class.sum=class.sum+1 where class.clno= :new.clno;
end;
-- ----------------------------
-- Checks structure for table STUDENT
-- ----------------------------
ALTER TABLE "SCOTT"."STUDENT" ADD CHECK (sex in('男','女'));
ALTER TABLE "SCOTT"."STUDENT" ADD CHECK ("SNAME" IS NOT NULL);
ALTER TABLE "SCOTT"."STUDENT" ADD CHECK ("SEX" IS NOT NULL);
ALTER TABLE "SCOTT"."STUDENT" ADD CHECK ("CLNO" IS NOT NULL);
ALTER TABLE "SCOTT"."STUDENT" ADD CHECK ("BIRTH" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table STUDENT
-- ----------------------------
ALTER TABLE "SCOTT"."STUDENT" ADD PRIMARY KEY ("SNO");

-- ----------------------------
-- Indexes structure for table TEACHER
-- ----------------------------

-- ----------------------------
-- Checks structure for table TEACHER
-- ----------------------------
ALTER TABLE "SCOTT"."TEACHER" ADD CHECK ("TNAME" IS NOT NULL);
ALTER TABLE "SCOTT"."TEACHER" ADD CHECK ("CNO" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table TEACHER
-- ----------------------------
ALTER TABLE "SCOTT"."TEACHER" ADD PRIMARY KEY ("TNO");
