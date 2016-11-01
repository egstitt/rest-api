drop table if exists users;
CREATE TABLE users (
    id integer primary key,
    firstname varchar(255),
    lastname varchar(255),
    username varchar(255),
    password varchar(255),
    email_address varchar(255),
    enabled boolean,
    create_date timestamp without time zone,
    create_user integer,
    update_date timestamp without time zone,
    update_user integer
);
insert into users(id,username,password,enabled) values(1, 'steve','steve',true);
insert into users(id,username,password,enabled) values(2, 'john','john',true);

drop table if exists authorities;
create table authorities(id integer primary key, username varchar(255), authority varchar(255), UNIQUE(username,authority));
insert into authorities(id,username,authority) values(1,'steve','admin');
insert into authorities(id,username,authority) values(2,'john','superadmin');

drop table if exists app_config;
CREATE TABLE app_config (
    id integer primary key,
    version_number varchar(50),
    create_date timestamp without time zone,
    create_user integer,
    update_date timestamp without time zone,
    update_user integer
);
insert into app_config(id, version_number) values(1, '1.0');
