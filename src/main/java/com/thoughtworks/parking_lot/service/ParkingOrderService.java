package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.expection.NotEnoughPositionExpection;
import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;


    public ParkingOrder save(ParkingOrder parkingOrder) {
        ParkingLot parkingLot = parkingOrder.getParkingLot();
        int usedPositionNumber = findUsedParkingOrderNumberByParkingLot(parkingLot);

        if (usedPositionNumber >= parkingLot.getCapacity()){
            throw new NotEnoughPositionExpection();
        }
        String orderId = UUID.randomUUID().toString();
        parkingOrder.setId(orderId);
        parkingOrder.setStartTime(new Date());
        parkingOrder.setOrderStatus(true);
        return parkingOrderRepository.save(parkingOrder);
    }

    public ParkingOrder updateParkingOrder(ParkingOrder parkingOrder) {
        parkingOrder.setEndTime(new Date());
        return parkingOrderRepository.save(parkingOrder);
    }

    private int findUsedParkingOrderNumberByParkingLot(ParkingLot parkingLot){
        return parkingOrderRepository.findAllByParkingLotAndAndOrderStatus(parkingLot, true).size();
    }
}
