package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.expection.NotEnoughPositionExpection;
import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingOrderController {

    @Autowired
    private ParkingOrderService parkingOrderService;

    @PostMapping("/orders")
    public ResponseEntity<ParkingOrder> createOrders(@RequestBody ParkingOrder parkingOrder) {
        try {
            ParkingOrder parkingOrder1 = parkingOrderService.save(parkingOrder);
            return new ResponseEntity<>(parkingOrder1, HttpStatus.OK);
        } catch (NotEnoughPositionExpection e) {
            System.err.print("停车场已经满");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/orders/{orderId}")
    public ParkingOrder takeCarUpdateOrders(@PathVariable String orderId, @RequestBody ParkingOrder parkingOrder) {
        parkingOrder.setId(orderId);
        return parkingOrderService.updateParkingOrder(parkingOrder);
    }
}
