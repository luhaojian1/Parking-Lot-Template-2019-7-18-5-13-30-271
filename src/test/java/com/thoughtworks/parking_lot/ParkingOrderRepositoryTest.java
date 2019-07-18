package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.repository.RecognitionSystemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingOrderRepositoryTest {
    @Autowired
    private RecognitionSystemRepository recognitionSystemRepository;

    @Test
    public void should_parking_order(){
        ParkingParkingOrderControllerTest orderControllerTest = new ParkingParkingOrderControllerTest();
        ParkingOrder parkingOrder = orderControllerTest.createRecognitionSystem("123", "a123", false);

        ParkingOrder parkingOrder1 = recognitionSystemRepository.save(parkingOrder);

        assertNotNull(parkingOrder1);
        assertEquals("123", parkingOrder1.getId());
        assertEquals("OOIDD", parkingOrder1.getParkingLot().getName());
    }

}
