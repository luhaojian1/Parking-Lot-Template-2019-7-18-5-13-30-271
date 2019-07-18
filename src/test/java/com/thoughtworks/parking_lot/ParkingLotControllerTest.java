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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void should_buy_parking_lot() throws Exception {

        ParkingLot parkingLot = createParkingLot( "OOCL", 200, "香洲区");
        when(parkingLotService.save(any(ParkingLot.class))).thenReturn(parkingLot);
        ResultActions resultActions = mvc.perform(post("/parkingLots").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"location\":\"fdsf\",\n" +
                "       \"name\":\"fsdf\",\n" +
                "       \"capacity\":200\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("OOCL")))
                .andExpect(jsonPath("$.capacity", is(200)))
                .andExpect(jsonPath("$.location", is("香洲区")));
    }

    @Test
    void should_sell_parking_lot() throws Exception {

        ResultActions resultActions = mvc.perform(delete("/parkingLots/p1"));
        resultActions.andExpect(status().isOk());
        verify(parkingLotService).deleteByName(anyString());
    }

    public ParkingLot createParkingLot( String name, int capacity, String location) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setCapacity(capacity);
        parkingLot.setLocation(location);
        return parkingLot;
    }

    @Test
    void should_return_parking_lots_by_pages_and_pageSize() throws Exception {

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(createParkingLot("OO15L", 200, "香洲区"));
        parkingLots.add(createParkingLot( "O14L", 200, "香洲区"));
        parkingLots.add(createParkingLot( "O12L", 200, "香洲区"));

        //when(parkingLotService.findParkingLotsByPageAndPageSize(1,15)).thenReturn();
        ResultActions resultActions = mvc.perform(post("/parkingLots").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"location\":\"fdsf\",\n" +
                "       \"name\":\"fsdf\",\n" +
                "       \"capacity\":200\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("OOCL")))
                .andExpect(jsonPath("$.capacity", is(200)))
                .andExpect(jsonPath("$.location", is("香洲区")));
    }

    @Test
    void should_increase_parking_lot_capacity() throws Exception {
        ParkingLot parkingLot = createParkingLot("OOCL", 200, "香洲区");
        when(parkingLotService.updateParingLot(any(ParkingLot.class))).thenReturn(parkingLot);
        ResultActions resultActions = mvc.perform(put("/parkingLots/1111").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "       \"location\":\"OOCL\",\n" +
                "       \"name\":\"hello\",\n" +
                "       \"capacity\":200\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("OOCL")))
                .andExpect(jsonPath("$.capacity", is(200)))
                .andExpect(jsonPath("$.location", is("香洲区")));
    }

}
