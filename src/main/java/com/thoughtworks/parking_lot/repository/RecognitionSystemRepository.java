package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.module.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecognitionSystemRepository extends JpaRepository<ParkingOrder, String> {

}
