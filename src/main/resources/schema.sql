drop table if exists users;
CREATE  TABLE users (
  username varchar(45) not null,
  password varchar(45) not null,
  enabled varchar not null default 1,
  primary key (username)
);
insert into users(username,password,enabled) values('bonjovi','burrito3',true);

drop table if exists authorities;
create table authorities(id integer primary key, username varchar(255), authority varchar(255), UNIQUE(username,authority));
insert into authorities(id,username,authority) values(1,'bonjovi','superadmin');

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
insert into account(id,username,password) values(1, 'bonjovi','burrito3');
drop sequence if exists sbc_account_id_seq;
create sequence sbc_account_id_seq;

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
drop sequence if exists sbc_app_config_id_seq;
create sequence sbc_app_config_id_seq;

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
drop sequence if exists sbc_sensor_point_id_seq;
create sequence sbc_sensor_point_id_seq;

drop table if exists gps_point;
CREATE TABLE gps_point (
    id integer primary key,
    time_stamp bigint,
    lat numeric(30, 6),
    lon numeric(30, 6),
    speed numeric(30, 6),
    elevation numeric(30, 6),
    heading numeric(30, 6),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists sbc_gps_point_id_seq;
create sequence sbc_gps_point_id_seq;

drop table if exists bike_settings;
CREATE TABLE bike_settings (
    id integer primary key,
    rider varchar(255),
    rider_note varchar(1000),
    accelerometer_rate integer,
    gyroscope_rate integer,
    gps_rate integer,
    bike_names varchar(1000),
    bike_sizes varchar(1000),
    wheel_names varchar(1000),
    tire_names varchar(1000),
    date_property timestamp without time zone,
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists bike_settings_id_seq;
create sequence bike_settings_id_seq;
insert into bike_settings(id, rider, accelerometer_rate, gyroscope_rate, gps_rate, bike_names, bike_sizes, wheel_names, tire_names)
values(1, 'bonjovi', 50, 25, 1, 'Mighty Bike 2000,Super Bike 3000,Crazy Bike 205', '100,105,205', 'Round wheels,Square wheels', 'Cool tires,Super tires,Wow tires')

