create table USR_CAR_BID
(
   USR_CAR_BID_ID integer not null AUTO_INCREMENT,
   USR_ID varchar(255) not null,
   CAR varchar(255) not null,
   BID_AMNT double not null,
   primary key(USR_CAR_BID_ID)
);



create table CAR_DTLS
(
   CAR_DTLS_ID integer not null AUTO_INCREMENT,
   CAR_CMPNY varchar(255) not null,
   CAR_MDL varchar(255) not null,
   CAR_AVLBTY char(1) not null,
   BASE_PRCE double not null,
   primary key(CAR_DTLS_ID)
);



insert into CAR_DTLS values(1,'Toyota', '2019 Toyota C-HR', 'N',10000);
insert into CAR_DTLS values(2,'Benz', '2019 Mercedes-Benz C-Class', 'Y',20000);
insert into CAR_DTLS values(3,'Nissan', '2018 Nissan GT-R', 'Y',15000);
insert into CAR_DTLS values(4,'Honda', '2018 Honda CR-V', 'Y',14000);
insert into CAR_DTLS values(5,'Hyundai', '2019 Hyundai Santa Fe', 'Y',12000);