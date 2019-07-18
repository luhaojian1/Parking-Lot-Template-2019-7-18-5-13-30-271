package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parkinglots")
    public ParkingLot buyParingLot(ParkingLot parkingLot) {
        return parkingLotService.save(parkingLot);
    }
}
