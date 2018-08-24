create table USR_CAR_BID
(
   USR_CAR_BID_ID integer not null AUTO_INCREMENT,
   USR_ID varchar(255) not null,
   CAR varchar(255) not null,
   BID_AMNT double not null,
   primary key(USR_CAR_BID_ID)
);