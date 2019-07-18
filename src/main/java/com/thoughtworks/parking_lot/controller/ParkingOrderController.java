package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping("/orders")
    public ParkingOrder createOrders(@RequestBody ParkingOrder parkingOrder){
        return parkingOrderService.save(parkingOrder);
    }

    @PutMapping("/orders/{orderId}")
    public ParkingOrder takeCarUpdateOrders(@PathVariable String orderId, @RequestBody ParkingOrder parkingOrder){
        parkingOrder.setId(orderId);
        return parkingOrderService.updateParkingOrder(parkingOrder);
    }
}
