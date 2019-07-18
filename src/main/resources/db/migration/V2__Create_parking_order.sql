create table `parking_order`(
    `id` varchar(20) primary key ,
    `parking_lot_name`  varchar (50)  not null ,
    `car_id` varchar (6) not null ,
    `start_time` TIMESTAMP not null,
    `end_time`  TIMESTAMP ,
    `order_status` BOOLEAN not null
);