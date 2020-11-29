create schema project_trial;
use project_trial;

create table source (
    source_id varchar(50),
    name varchar(50),
    primary key (source_id)
);

insert into source values ('R123','abc');
insert into source values ('D317','mno');
insert into source values ('R745','xyz');
insert into source values ('R908','klo');
insert into source values ('D635','ghi');
insert into source values ('R742','rst');
insert into source values ('D111','uvw');

create table river (
    river_id varchar(50),
    no_of_dams numeric(3,0),
    river_length numeric(6,2),
    primary key (river_id),
    foreign key (river_id) references source(source_id)
);

insert into river values ('R123',12,5000.45);
insert into river values ('R745',20,9762.73);
insert into river values ('R908',12,3425.79);
insert into river values ('R742',22,1234.92);

create table dam(
    dam_id varchar(50),
    water_level numeric(5,2),
    max_capacity numeric(5,2),
    primary key (dam_id),
    foreign key (dam_id) references source(source_id)
);

insert into dam values ('D317','345.3','500');
insert into dam values ('D635','896.7','900');
insert into dam values ('D111','533','750.23');

create table property(
    source_id varchar(50),
    inspection_date date,
    ph_level numeric(2,1),
    ppm numeric(3,0),
    contamination_level numeric(1,0),
    primary key (source_id,inspection_date),
    foreign key (source_id) references source(source_id)
);

insert into property values ('R123','2020-11-17',6.4,400,2);
insert into property values ('D317','2020-09-13',7.7,500,1);
insert into property values ('R123','2020-08-21',7.9,420,2.5);
insert into property values ('D317','2020-10-21',7.7,700,4.7);
insert into property values ('R745','2020-11-17',7.8,450,3);
insert into  property values ('R908','2020-09-14',8.9,430,4.5);
insert into property values ('D635','2020-07-09',5.6,459,5);
insert into property values  ('R742','2020-07-07',4,346,3.3);
insert into property values ('R908','2020-11-29',7.8,400,3);
insert into property values ('D111','2020-06-05',6.9,487,4.12);

create table area(
    location_id varchar(50),
    dam_id varchar(50),
    no_of_connections numeric(5,0),
    primary key (location_id),
    foreign key (dam_id) references dam(dam_id)
);

insert into area values ('L1','D111',47);
insert into area values ('L2','D111',27);
insert into area values ('L3','D317',60);
insert into area values ('L4','D635',57);

create table waste_water_management(
    plant_name varchar(50),
    plant_location_id varchar(50),
    volume_of_water_per_month numeric(8,0),
    primary key (plant_name),
    foreign key (plant_location_id) references area(location_id)
    );

insert into waste_water_management values ('wwtp1','L4',56000);
insert into waste_water_management values ('wwtp2','L3',56000);
insert into waste_water_management values ('wwtp3','L2',56000);
insert into waste_water_management values ('wwtp4','L1',56000);

create table purification (
    plant_name varchar(50),
    purification_method varchar(50),
    foreign key (plant_name) references waste_water_management(plant_name)
);

insert into purification values ('wwtp1','oxidation');
insert into purification values ('wwtp1','filtration');
insert into purification values ('wwtp2','sedimentation');
insert into purification values ('wwtp2','physical separation');
insert into purification values ('wwtp3','oxidation');
insert into purification values ('wwtp3','screening');
insert into purification values ('wwtp4','skimming');

create table account(
    username varchar(50) primary key,
    name varchar(50),
    password varchar(50),
    type varchar(50)
);

insert into account values ('wre1','Ram','pwdwre1','wre');
insert into account values ('wre1','Ram','pwdwre1','wre');
insert into account values ('public1','Arya','pwdpub1','public');
insert into account values ('public2','Neya','pwdpub2','public');

create table public(
    username varchar(50),
    no_of_connections numeric(2,0),
    door_num varchar(50),
    street varchar(50),
    locality varchar(50),
    foreign key (username) references account(username),
    primary key (username)
);

insert into public values ('public1',13,2,'VKK Menon Road','Gandhipuram');
insert into public values ('public2',32,7,'XYZ Street','Peelamedu');

create table employee(
    username varchar(50),
    emp_id varchar(50) primary key,
    name varchar(50),
    salary numeric(10,2),
    dob date,
    doj date
);

insert into employee values ('wre1','empwre1','55000','1997-09-07','2019-03-28');

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
);

insert into engineer values ('empwre1','wre');

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
    primary key (connection_id),
    foreign key (location_id) references area(location_id)
);

create table connection_req(
    connection_id varchar(50),
    type varchar(50),
    location_id varchar(50),
    status varchar(50),
    primary key (connection_id),
    foreign key (location_id) references area(location_id)
);

create table public_connection(
    username varchar(50),
    connection_id varchar(50),
    foreign key (connection_id) references connection(connection_id),
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

/*query*/
select * from property order by source_id,inspection_date desc;
alter table property modify contamination_level numeric(3,2);


create table budget_and_tally(
    alloted_to varchar(50),
    tally_id varchar(50),
    allotted_amount int,
    expenditure int,
    primary key (tally_id),
    foreign key (alloted_to) references engineer(emp_id)
);

create table complaints(
    username varchar(50),
    complaint_id varchar(50),
    issue varchar(50),
    complaint_status varchar(50),
    date_reg date,
    assigned_to varchar(50),
    primary key (complaint_id),
    foreign key (assigned_to) references engineer(emp_id),
    foreign key (username) references public(username)
);

insert into complaints values ('public1','c1','ground water contamination','pending','2020-06-23','empwre1');
insert into complaints values ('public1','c2','unlicensed industry','pending','2020-11-02','empwre1');
insert into complaints values ('public2','c3','sand laundering','pending','2020-10-24','empwre1');
insert into complaints values ('public2','c4','unlicensed industry','complete','2020-04-09','empwre1');


create table project(
    project_id varchar(50),
    emp_id varchar(50),
    project_name varchar(50),
    approval varchar(50),
    start_date date,
    due_date date,
    primary key (project_id),
    foreign key (emp_id) references employee(emp_id)
);

create table complaint_resolution(
    complaint_id varchar(50),
    project_id varchar(50),
    solution varchar(100),
    foreign key (complaint_id) references complaints(complaint_id),
    foreign key (project_id) references project(project_id),
    primary key (project_id)
);

create table development_project(
    project_id varchar(50),
    description varchar(100),
    foreign key (project_id) references project(project_id),
    primary key (project_id)
);


create table purchase(
    purchase_id varchar(50),
    emp_id varchar(50),
    procurement_date date,
    issue_date date,
    status varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (purchase_id)
    );

create table inventory(
    purchase_id varchar(50),
    serial_no varchar(50),
    num_prod int,
    spec varchar(100),
    prod_name varchar(50),
    upgrade date,
    price int,
    primary key (serial_no),
    foreign key (purchase_id) references purchase(purchase_id)
);




