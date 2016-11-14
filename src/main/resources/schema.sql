drop table if exists users;
CREATE  TABLE users (
  username varchar(45) not null,
  password varchar(45) not null,
  enabled varchar not null default 1,
  primary key (username)
);
insert into users(username,password,enabled) values('steve','steve',true);
insert into users(username,password,enabled) values('john','john',true);

drop table if exists authorities;
create table authorities(id integer primary key, username varchar(255), authority varchar(255), UNIQUE(username,authority));
insert into authorities(id,username,authority) values(1,'steve','admin');
insert into authorities(id,username,authority) values(2,'john','superadmin');

drop table if exists account;
CREATE TABLE account (
    id integer primary key,
    firstname varchar(255),
    lastname varchar(255),
    username varchar(255),
    password varchar(255),
    email_address varchar(255),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
insert into account(id,username,password) values(1, 'steve','steve');
insert into account(id,username,password) values(2, 'john','john');

drop table if exists app_config;
CREATE TABLE app_config (
    id integer primary key,
    version_number varchar(50),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
insert into app_config(id, version_number) values(1, '1.0');

drop table if exists sensor_point;
CREATE TABLE sensor_point (
    id integer primary key,
    time_stamp bigint,
    acc_x numeric(30, 6),
    acc_y numeric(30, 6),
    acc_z numeric(30, 6),
    gyr_x numeric(30, 6),
    gyr_y numeric(30, 6),
    gyr_z numeric(30, 6),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);