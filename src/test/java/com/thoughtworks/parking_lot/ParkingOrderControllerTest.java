package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.controller.ParkingOrderController;
import com.thoughtworks.parking_lot.expection.NotEnoughPositionExpection;
import com.thoughtworks.parking_lot.module.ParkingLot;
import com.thoughtworks.parking_lot.module.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingOrderController.class)
@ExtendWith(SpringExtension.class)
public class ParkingOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingOrderService parkingOrderService;


    @Test
    void should_create_order_form() throws Exception {

        ParkingOrder parkingOrder = createOrder("123456","asd123",true, "OOIDD");
        when(parkingOrderService.save(any(ParkingOrder.class))).thenReturn(parkingOrder);
        ResultActions resultActions = mvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"carId\":\"123456\"\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("123456")))
                .andExpect(jsonPath("$.carId", is("asd123")))
                .andExpect(jsonPath("$.orderStatus", is(true)))
                .andExpect(jsonPath("$.parkingLot.name", is("OOIDD")));
    }

    @Test
    void should_update_order_form() throws Exception {

        ParkingOrder parkingOrder = createOrder("123456","asd123",false, "OOIDD");
        parkingOrder.setEndTime(new Date());
        when(parkingOrderService.updateParkingOrder(any(ParkingOrder.class))).thenReturn(parkingOrder);
        ResultActions resultActions = mvc.perform(put("/orders/4561").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"carId\":\"123456\"\n" +
                "    }"));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("123456")))
                .andExpect(jsonPath("$.carId", is("asd123")))
                .andExpect(jsonPath("$.orderStatus", is(false)))
                .andExpect(jsonPath("$.parkingLot.name", is("OOIDD")));
    }

    @Test
    void should_throw_not_enough_position_expection_when_full_capacity() throws Exception {

        ParkingOrder parkingOrder = createOrder("123456","asd123",false, "OOIDD");
        parkingOrder.setEndTime(new Date());
        when(parkingOrderService.save(any(ParkingOrder.class))).thenThrow(NotEnoughPositionExpection.class);
        ResultActions resultActions = mvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "       \"carId\":\"123456\"\n" +
                "    }"));

        resultActions.andExpect(status().isBadRequest());
    }

     ParkingOrder createOrder(String id, String carId, boolean orderStatus, String parkingLotName){
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setId(id);
        parkingOrder.setCarId(carId);
        parkingOrder.setStartTime(new Date());
        parkingOrder.setOrderStatus(orderStatus);
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(parkingLotName);
        parkingLot.setCapacity(300);
        parkingLot.setLocation("zhuHai");
        parkingOrder.setParkingLot(parkingLot);
        return parkingOrder;
    }
}
