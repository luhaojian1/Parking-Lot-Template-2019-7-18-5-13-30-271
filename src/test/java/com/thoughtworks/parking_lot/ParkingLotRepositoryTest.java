package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    public void should_create_parking_lot(){


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("停车场1");
        parkingLot.setCapacity(200);
        parkingLot.setLocation("南软");

        ParkingLot targetParkingLot = parkingLotRepository.save(parkingLot);

        assertEquals("停车场1",targetParkingLot.getName());
    }

    @Test
    public void should_delete_parking_lot_by_name(){


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("停车场1");
        parkingLot.setCapacity(200);
        parkingLot.setLocation("南软");
        ParkingLot parkingLot2 = parkingLotRepository.save(parkingLot);

        parkingLotRepository.deleteById(parkingLot.getName());
        ParkingLot parkingLot1 = parkingLotRepository.findById(parkingLot.getName()).orElse(null);

        assertEquals(parkingLot2.getCapacity(), parkingLot.getCapacity());
        assertNull(parkingLot1);
    }

    @Test
    public void should_return_parking_lots_by_pages_and_pageSize(){

        parkingLotRepository.save(createParkingLot("停车场2",200,"香洲区"));
        parkingLotRepository.save(createParkingLot("停车场1",200,"香洲区"));

        Pageable pageable = PageRequest.of(1,15);
        Page<ParkingLot> parkingLots = parkingLotRepository.findAll(pageable);

        assertEquals(parkingLots.getTotalElements(), 2);
    }

    @Test
    public void should_update_parking_lot_capacity(){

        ParkingLot parkingLot = createParkingLot("停车场2",200,"香洲区");
        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);

        parkingLot1.setCapacity(1000);
        parkingLotRepository.save(parkingLot1);
        ParkingLot targetParingLot = parkingLotRepository.findById("停车场2").orElse(null);

        assertNotNull(targetParingLot);
        assertEquals(1000, targetParingLot.getCapacity());
    }

    public ParkingLot createParkingLot( String name, int capacity, String location) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setCapacity(capacity);
        parkingLot.setLocation(location);
        return parkingLot;
    }
}
