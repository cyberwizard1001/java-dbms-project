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
    ph_level numeric(3,2),
    ppm numeric(3,0),
    contamination_level numeric(3,2),
    primary key (source_id,inspection_date),
    foreign key (source_id) references source(source_id)
);

insert into property values ('R123','2020-11-17',6.43,400,2.77);
insert into property values ('D317','2020-09-13',7.17,500,1.5);
insert into property values ('R123','2020-08-21',7.91,420,2.5);
insert into property values ('D317','2020-10-21',7.17,700,4.7);
insert into property values ('R745','2020-11-17',7.8,450,3);
insert into property values ('R908','2020-09-14',8.9,430,4.51);
insert into property values ('D635','2020-07-09',5.65,459,5);
insert into property values  ('R742','2020-07-07',4.77,346,3.32);
insert into property values ('R908','2020-11-29',7.68,400,3.03);
insert into property values ('D111','2020-06-05',6.89,487,4.12);

create table area(
    location_id varchar(50),
    dam_id varchar(50),
    no_of_connections numeric(5,0),
    primary key (location_id),
   foreign key (dam_id) references dam(dam_id)
);

drop table area;

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

select * from account;

insert into account values ('wre1','Ram','pwdwre1','wre');
insert into account values ('public1','Arya','pwdpub1','public');
insert into account values ('public2','Neya','pwdpub2','public');
insert into account values ('ad1','Sonu','pwdad1','admin');
insert into account values ('f1','Santosh','pwdf1','finance');
insert into account values ('public3','Maya','pwdpub3','public');
insert into account values ('public4','Diya','pwdpub4','public');
insert into account values ('pe1','Chandru','pwdpe1','project engineer');
insert into account values ('sys01','Nirmal','pwdsys01','sysadmin');

select * from complaints;

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
insert into public values ('public3',16,7,'T.T Street','Ramnagar');
insert into public values ('public4',32,4,'Nehru Street','Avinashi');

create table employee(
    username varchar(50),
    emp_id varchar(50) primary key,
    name varchar(50),
    salary numeric(10,2),
    dob date,
    doj date,
    foreign key (username) references account(username)
);

select * from employee;

insert into employee values ('wre1','empwre1','Ram',55000,'1997-09-07','2019-03-28');
insert into employee values ('ad1','empad1','Tarsana',79000,'1982-08-07','2005-07-03');
insert into employee values ('f1','empf1','Rithika',47000,'1992-08-06','2018-09-08');
insert into employee values ('pe1','emppe1','Vinaya',67000,'1995-01-03','2017-08-23');
insert into employee values ('sys01','empsys01','Nirmal',65000,'1997-09-07','2019-03-28');

create table admin(
    emp_id varchar(50),
    channel_of_entry varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
);

insert into admin values ('empad1','EX-IRS');

create table finance(
    emp_id varchar(50),
    branch varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
);

insert into finance values ('empf1','finance');

create table engineer(
    emp_id varchar(50),
    branch varchar(50),
    foreign key (emp_id) references employee(emp_id),
    primary key (emp_id)
);

insert into engineer values ('empwre1','wre');
insert into engineer values ('emppe1','pe');

create table key_card(
    keycard_id varchar(50),
    issue date,
    emp_id varchar(50),
    primary key (keycard_id),
    foreign key (emp_id) references employee(emp_id)
);

drop table key_card;

create table connection(
    connection_id varchar(50),
    type varchar(50),
    location_id varchar(50),
    primary key (connection_id),
    foreign key (location_id) references area(location_id)
);
-- drop table connection;

insert into connection values ('CO23','domestic','L1');
insert into connection values ('CO88','commercial','L2');
insert into connection values ('CO35','domestic','L3');
insert into connection values ('CO34','domestic','L4');

create table connection_req(
    username varchar(50),
    type varchar(50),
    location_id varchar(50),
    status varchar(50),
    primary key (username),
    foreign key (location_id) references area(location_id)
);

-- insert into connection_req values('public1',)

-- drop table connection_req;

insert into connection_req values('public1','domestic','L1','pending');

create table public_connection(
    username varchar(50),
    connection_id varchar(50),
    foreign key (connection_id) references connection(connection_id),
    foreign key (username) references account(username)
);
-- drop table public_connection;
insert into public_connection values ('public1','CO23');
insert into public_connection values ('public2','CO88');
insert into public_connection values ('public3','CO34');
insert into public_connection values ('public4','CO34');

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

insert into payment values ('#12345',null,'online',null,'2020-11-12',null,50);
insert into payment values ('#62635','#B01','offline',2300,'2020-11-29','2020-11-05',50);
insert into payment values ('#42935','#B08','online',1700,'2020-11-09','2020-10-30',60);
insert into payment values ('#91263','#B15','offline',1500 ,'2020-10-25','2020-10-01',48);

create table public_payment(
    username varchar(50),
    transaction_id varchar(50),
    foreign key (transaction_id) references payment(transaction_id),
    foreign key (username) references account(username)
);

insert into public_payment values ('public1','#12345');
insert into public_payment values ('public2','#62635');
insert into public_payment values ('public3','#42935');
insert into public_payment values ('public4','#91263');

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

select * from project;
select * from budget_and_tally;
insert into budget_and_tally values ('empwre1','tp01',50000,50000);
insert into budget_and_tally values ('empwre1','tp05',45000,45000);
insert into budget_and_tally values ('empwre1','ts01',60000,60000);

create table complaints(
    username varchar(50),
    complaint_id varchar(50),
    issue varchar(50),
    complaint_status varchar(50),
    date_reg date,
    assigned_to varchar(50),
    primary key (complaint_id),
    foreign key (assigned_to) references employee(emp_id),
    foreign key (username) references public(username)
);

select * from complaints;
select substring(complaint_id,2) AS ExtractString FROM complaints order by ExtractString desc;
insert into complaints values ('public1','c1','ground water contamination','pending','2020-06-23','empwre1');
insert into complaints values ('public1','c2','unlicensed industry','pending','2020-11-02','empwre1');
insert into complaints values ('public2','c3','sand laundering','pending','2020-10-24','empwre1');
insert into complaints values ('public2','c4','unlicensed industry','complete','2020-04-09','empwre1');
insert into complaints values ('public3','c5','connection problem','pending','2020-09-21','emppe1');
insert into complaints values ('public1','c6','p1','pending','2020-09-09','empad1');
update complaints set assigned_to='empad1' where complaint_id='c0';
delete from complaints where complaint_id='c6';

--- create table project(
    --- project_id varchar(50),
    --- emp_id varchar(50),
    --- project_name varchar(50),
    --- approval varchar(50),
    --- start_date date,
    --- due_date date,
    --- primary key (project_id),
    --- foreign key (emp_id) references employee(emp_id)
--- );

--- RUN THIS ---

drop table project;

create table project(
project_id varchar(50),
emp_id varchar(50),
project_name varchar(50),
approval varchar(50),
project_stat varchar(50),
start_date date,
due_date date,
primary key (project_id),
foreign key (emp_id) references employee(emp_id)
);

insert into project values ('p1','emppe1','dam construction','pending','pending','2020-06-23','2020-12-15');
insert into project values ('p2','emppe1','irrigation project','pending','pending','2020-11-02','2021-05-09');
insert into project values ('p3','emppe1','rejuvenate lake','complete','completed','2020-10-24','2021-03-06');
insert into project values ('p4','emppe1','checked /altered connections','complete','pending','2020-10-01','2020-11-01');

create table complaint_resolution(
    complaint_id varchar(50),
    project_id varchar(50),
    solution varchar(100),
    foreign key (complaint_id) references complaints(complaint_id),
    foreign key (project_id) references project(project_id),
    primary key (project_id)
);

insert into complaint_resolution values ('c1','p4','sol2');

create table development_project(
    project_id varchar(50),
    description varchar(100),
    foreign key (project_id) references project(project_id),
    primary key (project_id)
);

insert into development_project values ('p1','desc1');
insert into development_project values ('p2','desc2');
insert into development_project values ('p3','desc3');

create table purchase(
  purchase_id varchar(50),
  emp_id varchar(50),
 -- procurement_date date,
  application_date date,
  issue_date date,
  status varchar(50),
  foreign key (emp_id) references employee(emp_id),
  primary key (purchase_id)
    );

-- drop table purchase;

insert into purchase values ('purchase0001','empsys01','2020-03-09','2020-04-11','approved');
insert into purchase values ('purchase0002','empsys01','2020-02-04',NULL,'pending');
insert into purchase values ('purchase0003','empsys01','2020-05-04',NULL,'pending');
insert into purchase values ('purchase0004','empsys01','2020-03-04',NULL,'pending');
insert into purchase values ('purchase0005','empsys01','2019-03-09','2019-04-11','rejected');

-- update purchase set status = 'pending' where purchase_id = 'purchase0002';
select * from purchase where status = 'pending';

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

-- drop table inventory;

insert into inventory values('purchase0001','SDFJSDGRKF',1,'6th Gen i3, 8 gigs DDR4, 500GB HDD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','SDFJSDGRJH',1,'6th Gen i3, 8 gigs DDR4, 500GB HDD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','SDFJSSDRKF',1,'6th Gen i3, 8 gigs DDR4, 500GB HDD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','SDFJSXGRKF',1,'6th Gen i5, 16 gigs DDR4, 500GB SSD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','FGFJSDGRKF',1,'6th Gen i5, 12 gigs DDR4, 500GB SSD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','TJKJSDGRKF',1,'6th Gen i3, 4 gigs DDR4, 500GB HDD, GigaBit LAN','HP Pro Desktop','2022-11-20',30000);
insert into inventory values('purchase0001','RGRT123GFSF',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);
insert into inventory values('purchase0001','RGRT123GFSG',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);
insert into inventory values('purchase0001','RGRT123GFSH',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);
insert into inventory values('purchase0001','RGRT123GFSI',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);
insert into inventory values('purchase0001','RGRT123GFSJ',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);
insert into inventory values('purchase0001','RGRT123GFSK',1,'Laser, 18 ppm','HP LaserJet M1136 MFP','2022-05-16',13000);


insert into key_card values ('K29112020E01','2020-11-29','empwre1');
insert into key_card values ('K29112020E02','2020-11-29','empf1');
insert into key_card values ('K29112020E03','2020-11-29','empad1');
insert into key_card values ('K29112020E04','2020-11-29','empwre1');
insert into key_card values ('K29112020E05','2020-11-29','empwre1');


select * from complaints;
select * from property;
select * from waste_water_management;
select * from purification;
select * from property;
select * from employee;


select * from public;
select * from connection;
select * from connection_req;
select * from public_connection;

select substring(connection_id,3) AS ExtractString FROM connection order by ExtractString desc;