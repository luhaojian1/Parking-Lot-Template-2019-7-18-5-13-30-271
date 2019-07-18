create table `parking_lot`(
    `id` varchar(20) primary key ,
    `name`  varchar (50) unique not null ,
    `capacity` int not null ,
    `location` varchar (50) not null
);