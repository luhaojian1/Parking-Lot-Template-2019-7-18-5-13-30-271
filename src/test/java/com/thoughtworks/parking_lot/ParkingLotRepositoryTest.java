package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    public void should_create_parking_lot(){


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId("1111");
        parkingLot.setName("停车场1");
        parkingLot.setCapacity(200);
        parkingLot.setLocation("南软");

        ParkingLot targetParkingLot = parkingLotRepository.save(parkingLot);

        assertEquals("停车场1",targetParkingLot.getName());
    }

    @Test
    public void should_delete_parking_lot_by_id(){


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId("1111");
        parkingLot.setName("停车场1");
        parkingLot.setCapacity(200);
        parkingLot.setLocation("南软");
        ParkingLot parkingLot2 = parkingLotRepository.save(parkingLot);

        parkingLotRepository.deleteById(parkingLot.getId());
        ParkingLot parkingLot1 = parkingLotRepository.findById(parkingLot.getId()).orElse(null);

        assertEquals(parkingLot2.getCapacity(), parkingLot.getCapacity());
        assertNull(parkingLot1);
    }
}
