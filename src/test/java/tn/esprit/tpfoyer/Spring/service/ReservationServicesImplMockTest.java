package tn.esprit.tpfoyer.Spring.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ReservationRestController.class)
public class ReservationServicesImplMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetReservations() throws Exception {
        Reservation reservation = new Reservation("1", new Date(), true, Collections.emptySet());
        List<Reservation> reservations = List.of(reservation);

        when(reservationService.retrieveAllReservations()).thenReturn(reservations);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/retrieve-all-reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("1"))
                .andDo(print());
    }

    @Test
    public void testRetrieveReservation() throws Exception {
        Reservation reservation = new Reservation("1", new Date(), true, Collections.emptySet());

        when(reservationService.retrieveReservation("1")).thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/retrieve-reservation/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("1"))
                .andDo(print());
    }

    @Test
    public void testAddReservation() throws Exception {
        Reservation reservation = new Reservation("1", new Date(), true, Collections.emptySet());

        when(reservationService.addReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservation/add-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReservation").value("1"))
                .andDo(print());
    }


    @Test
    public void testRemoveReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/remove-reservation/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testModifyReservation() throws Exception {
        Reservation reservation = new Reservation("1", new Date(), true, Collections.emptySet());

        when(reservationService.modifyReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/modify-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReservation").value("1"))
                .andDo(print());
    }

}
