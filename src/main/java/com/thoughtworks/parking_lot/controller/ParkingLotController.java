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

    @DeleteMapping("/parkingLots/{parkingLotId}")
    public void sellParingLot(@PathVariable String parkingLotId) {
        parkingLotService.deleteById(parkingLotId);
    }

    @PutMapping("/parkingLots/{parkingLotId}")
    public ParkingLot increaseParingLotCapacity(@PathVariable String parkingLotId, @RequestBody ParkingLot parkingLot) {
        parkingLot.setId(parkingLotId);
        return parkingLotService.updateParingLot(parkingLot);
    }

}
