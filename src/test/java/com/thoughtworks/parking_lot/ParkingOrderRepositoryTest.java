package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingOrderRepositoryTest {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Test
    public void should_parking_order(){
        ParkingParkingOrderControllerTest orderControllerTest = new ParkingParkingOrderControllerTest();
        ParkingOrder parkingOrder = orderControllerTest.createRecognitionSystem("123", "a123", false);

        ParkingOrder parkingOrder1 = parkingOrderRepository.save(parkingOrder);

        assertNotNull(parkingOrder1);
        assertEquals("123", parkingOrder1.getId());
        assertEquals("OOIDD", parkingOrder1.getParkingLot().getName());
    }

    @Test
    public void should_take_car_update_parking_order_status(){
        ParkingParkingOrderControllerTest orderControllerTest = new ParkingParkingOrderControllerTest();
        ParkingOrder parkingOrder = orderControllerTest.createRecognitionSystem("123", "a123", true);
        ParkingOrder parkingOrder1 = parkingOrderRepository.save(parkingOrder);
        parkingOrder1.setOrderStatus(false);
        parkingOrder1.setEndTime(new Date());

        ParkingOrder targetParkingOrder = parkingOrderRepository.save(parkingOrder1);

        assertNotNull(targetParkingOrder);
        assertEquals("123", targetParkingOrder.getId());
        assertFalse(targetParkingOrder.isOrderStatus());

    }

}
