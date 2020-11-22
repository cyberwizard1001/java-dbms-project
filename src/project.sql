create schema project_trial;


use project_trial;

create table login(
    username varchar(45),
    password varchar(45)
);

insert into login values ('nirmalk','password');

select * from login;

create table source (
    source_id varchar(50),
    name varchar(50)
);

alter table source add constraint source_pk primary key (source_id);

create table river (
    river_id varchar(50),
    no_of_dams numeric(3,0),
    river_length numeric(6,2),
    primary key (river_id)
);

create table dam(
    dam_id varchar(50),
    water_level numeric(5,2),
    max_capacity numeric(5,2),
    primary key (dam_id)
);

create table property(
    source_id varchar(50),
    inspection_date date,
    ph_level numeric(2,1),
    ppm numeric(3,0),
    contamination_level numeric(1,0),
    primary key (source_id,inspection_date)
);

create table area(
    location_id varchar(50),
    dam_id varchar(50),
    scarcity_level numeric(1,0),
    time_of_supply numeric(4,0),
    no_of_connections numeric(5,0),
    primary key (location_id)
);

create table waste_water_management(
    plant_name varchar(50),
    plant_location_id varchar(50),
    volume_of_water_per_month numeric(8,0),
    primary key (plant_name)
    );

create table purification (
    plant_name varchar(50),
    purification_method varchar(50),
    primary key (plant_name)
);

create table account(
    username varchar(50) primary key,
    name varchar(50),
    password varchar(50)
);

create table public(
    username varchar(50),
    no_of_connections numeric(2,0),
    door_num varchar(50),
    street varchar(50),
    locality varchar(50),
    foreign key (username) references account(username),
    primary key (username)
);


create table employee(
    username varchar(50),
    emp_id varchar(50) primary key,
    name varchar(50),
    salary numeric(10,2),
    dob date,
    doj date
);

create table admin(
    emp_id varchar(50),
    channel_of_entry varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
);

create table finance(
    emp_id varchar(50),
    branch varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
);

create table engineer(
    emp_id varchar(50),
    branch varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
)

create table key_card(
    keycard_id varchar(50),
    issue varchar(50),
    emp_id varchar(50),
    primary key (keycard_id),
    foreign key (emp_id) references employee(emp_id)
);

create table connection(
    connection_id varchar(50),
    type varchar(50),
    location_id varchar(50),
    primary key (connection_id)
);

create table public_connection(
    username varchar(50),
    connection_id varchar(50),
    foreign key (username) references account(username)
);

create table payment(
    transaction_id varchar(50),
    bill_id varchar(50),
    payment_mode varchar(50),
    amt_paid numeric(7,2),
    due_date date,
    pay_date date,
    units_consumed numeric(5,2),
    primary key (transaction_id)
);

create table public_payment(
    username varchar(50),
    transaction_id varchar(50),
    foreign key (transaction_id) references payment(transaction_id),
    foreign key (username) references account(username)
);

create table project_tier(
    project_id char(50) primary key,
    project_status varchar(100),
    budget number (20),
    emp_id varchar(50),
    foreign key (emp_id) references employee(emp_id),
);