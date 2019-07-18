package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.module.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, String> {
    List<ParkingOrder> findAllByParkingLotAndAndOrderStatus(ParkingLot parkingLot, Boolean orderStatus);
}
