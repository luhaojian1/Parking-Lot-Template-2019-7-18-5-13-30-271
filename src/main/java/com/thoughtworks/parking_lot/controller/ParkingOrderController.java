package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping("/orders")
    private ParkingOrder createOrders(@RequestBody ParkingOrder parkingOrder){
        return parkingOrderService.save(parkingOrder);
    }
}
