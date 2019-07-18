package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLot save(ParkingLot parkingLot) {
        if (parkingLot.getCapacity() < 0){
            return null;
        }
        return parkingLotRepository.save(parkingLot);
    }

    public void deleteByName(String parkingLotName) {
        parkingLotRepository.deleteById(parkingLotName);
    }

    public Page<ParkingLot> findParkingLotsByPageAndPageSize(int page, int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize);
        return parkingLotRepository.findAll(pageable);
    }

    public ParkingLot updateParingLot(ParkingLot parkingLot) {
        if (parkingLot.getCapacity()<0){
            return null;
        }
        return parkingLotRepository.save(parkingLot);
    }
}
