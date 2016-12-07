drop table if exists users;
CREATE  TABLE users (
  username varchar(45) not null,
  password varchar(45) not null,
  enabled varchar not null default 1,
  primary key (username)
);

drop table if exists authorities;
create table authorities(id integer primary key, username varchar(255), authority varchar(255), UNIQUE(username,authority));

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
insert into account(id,username,password,firstname,lastname,email_address) values(1, 'bonjovi','burrito3', 'Jon', 'Bon Jovi', 'bonjovi@gmail.com');
drop sequence if exists account_id_seq;
create sequence account_id_seq;
select nextval('account_id_seq');

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
drop sequence if exists app_config_id_seq;
create sequence app_config_id_seq;
select nextval('app_config_id_seq');
