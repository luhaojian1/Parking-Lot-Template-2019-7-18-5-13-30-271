package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parkingLots")
    public ParkingLot buyParingLot(ParkingLot parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    @DeleteMapping("/parkingLots/{parkingLotName}")
    public void sellParingLot(@PathVariable String parkingLotName) {
        parkingLotService.deleteByName(parkingLotName);
    }

    @PutMapping("/parkingLots/{parkingLotName}")
    public ParkingLot increaseParingLotCapacity(@PathVariable String parkingLotName, @RequestBody ParkingLot parkingLot) {
        parkingLot.setName(parkingLotName);
        return parkingLotService.updateParingLot(parkingLot);
    }

}
