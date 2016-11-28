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
select nextval('sbc_account_id_seq');

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
drop sequence if exists sbc_bike_settings_id_seq;
create sequence sbc_bike_settings_id_seq;
insert into bike_settings(id, rider, accelerometer_rate, gyroscope_rate, gps_rate, bike_names, bike_sizes, wheel_names, tire_names)
values(1, 'bonjovi', 50, 25, 1, 'Mighty Bike 2000,Super Bike 3000,Crazy Bike 205', '100,105,205', 'Round wheels,Square wheels', 'Cool tires,Super tires,Wow tires');
select nextval('sbc_bike_settings_id_seq');

drop table if exists ride;
CREATE TABLE ride (
    id integer primary key,
    date_property bigint,
    mac_address varchar(100),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists sbc_ride_id_seq;
create sequence sbc_ride_id_seq;

drop table if exists sensor_point;
drop table if exists acc_point;
CREATE TABLE acc_point (
    id integer primary key,
    ride_id integer,
    time_stamp bigint,
    accx numeric(30, 6),
    accy numeric(30, 6),
    accz numeric(30, 6),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists sbc_sensor_point_id_seq;
drop sequence if exists sbc_acc_point_id_seq;
create sequence sbc_acc_point_id_seq;

drop table if exists gps_point;
CREATE TABLE gps_point (
    id integer primary key,
    ride_id integer,
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

drop table if exists reed_point;
CREATE TABLE reed_point (
    id integer primary key,
    ride_id integer,
    time_stamp bigint,
    adc integer,
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists sbc_reed_point_id_seq;
create sequence sbc_reed_point_id_seq;

drop table if exists gyro_point;
CREATE TABLE gyro_point (
    id integer primary key,
    ride_id integer,
    time_stamp bigint,
    gyrx numeric(30, 6),
    gyry numeric(30, 6),
    gyrz numeric(30, 6),
    create_date timestamp without time zone,
    create_account integer,
    update_date timestamp without time zone,
    update_account integer
);
drop sequence if exists sbc_gyro_point_id_seq;
create sequence sbc_gyro_point_id_seq;
