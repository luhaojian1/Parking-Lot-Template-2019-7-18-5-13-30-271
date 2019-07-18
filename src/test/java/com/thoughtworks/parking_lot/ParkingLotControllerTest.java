package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingLotController.class)
@ExtendWith(SpringExtension.class)

public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    public void should_buy_parking_lot() throws Exception {


        ParkingLot parkingLot = createParkingLot("1111", "OOCL", 200, "香洲区");
        when(parkingLotService.save(any(ParkingLot.class))).thenReturn(parkingLot);
        ResultActions resultActions = mvc.perform(post("/parkinglots").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"location\":\"fdsf\",\n" +
                "       \"name\":\"fsdf\"\n" +
                "       \"capacity\":200\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name" , is("OOCL")))
                .andExpect(jsonPath("$.capacity", is(200)))
                .andExpect(jsonPath("$.location", is("香洲区")));
    }

    @Test
    public void should_sell_parking_lot() throws Exception {


         mvc.perform(delete("/parkinglots/111"));

        verify(parkingLotService).deleteById(anyString());
    }

    public ParkingLot createParkingLot(String id, String name, int capacity, String location){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(id);
        parkingLot.setName(name);
        parkingLot.setCapacity(capacity);
        parkingLot.setLocation(location);
        return parkingLot;
    }
}
