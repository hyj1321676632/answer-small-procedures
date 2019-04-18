# answer-small-procedures
基于springboot开发的答题小程序

本套系统数据库选用的是oracle，项目中涉及到的sql脚本如下：

--用户表

create table user_info
(
       id number(10) primary key,
       user_id varchar2(255) not null,
       user_password varchar2(255) not null,
       user_name varchar2(20) not null,
       user_school varchar2(100) not null,
       user_sex varchar2(10) not null,
       user_role varchar2(20) not null,
       create_date date
)

--创建序列

create sequence user_info$seq
minvalue 1
maxvalue 99999999
start with 1
increment by 1
cache 20;

--管理员账号

insert into user_info (ID, USER_ID, USER_PASSWORD, USER_NAME, USER_SCHOOL, USER_SEX, USER_ROLE, CREATE_DATE)
values (1, 'admin', '123', '管理员', '无', '男', 'admin', to_date('16-04-2019 09:38:24', 'dd-mm-yyyy hh24:mi:ss'));

--题目表

create table test_title
(
    title_id number(10) primary key,
    title_header varchar2(255) not null,
    option_a varchar2(255) not null,
    option_b varchar2(255) not null,
    option_c varchar2(255) not null,
    option_d varchar2(255) not null,
    title_answer varchar2(10) not null,
    parse_text varchar2(255) not null
)

--创建序列

create sequence test_title$seq
minvalue 1
maxvalue 99999999
start with 1
increment by 1
cache 20;

--用户题目关系表

create table user_title
(
       id number(10) primary key,
       user_id varchar2(255) not null,
       title_id number(10) not null,
       test_result varchar2(10) not null,
       create_date Date
)

--创建序列

create sequence user_title$seq
minvalue 1
maxvalue 99999999
start with 1
increment by 1
cache 20; 
 
--创建备忘录表  

create table user_notes
(
  id number(10) primary key,
  content varchar2(255),
  create_date Date
)

--表序列

create sequence user_notes$seq
minvalue 1
maxvalue 99999999
start with 1
increment by 1
cache 20;

--答题记录表

create table choose_result
(
       id number(10) primary key ,
       title_id number(10) ,
       choose_answer varchar(10) 
)

--序列

create sequence choose_result$seq
minvalue 1
maxvalue 99999999
start with 1
increment by 1
cache 20;



