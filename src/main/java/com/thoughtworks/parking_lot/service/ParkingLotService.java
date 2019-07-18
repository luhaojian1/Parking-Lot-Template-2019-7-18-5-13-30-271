package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot save(ParkingLot parkingLot) {
        if (parkingLot.getCapacity() < 0){
            return null;
        }
        String id = UUID.randomUUID().toString();
        parkingLot.setId(id);
        return parkingLotRepository.save(parkingLot);
    }
}
