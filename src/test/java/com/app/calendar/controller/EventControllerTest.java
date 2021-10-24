package com.app.calendar.controller;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventService eventService;
    String[] user = {"U3","U2"};
    List<String> userList = Arrays.asList(user);
    GetEventResponseDto eventResponseDto = new GetEventResponseDto(1L,"Meeting","Meeting 1", LocalDateTime.of(2021,10,25,2,30,0),LocalDateTime.of(2021,10,25,3,0,0),"R1","U1", userList);

    @Test
    public void getEvent_success() throws Exception {
        Mockito.when(eventService.getEvent(eventResponseDto.getEventId())).thenReturn(eventResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/eventDetail/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventTitle").value("Meeting 1"));
    }

    @Test
    public void getEvent_failure() throws Exception {
        Mockito.when(eventService.getEvent(eventResponseDto.getEventId())).thenReturn(eventResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/eventDetail/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto("M","Meeting 1",LocalDateTime.of(2021,10,26,2,30,0),30,"R1","U2",userList);
    @Test
    public void createEvent_success() throws Exception {
        Mockito.doNothing().when(eventService).createEvent(createEventRequestDto);
        eventService.createEvent(createEventRequestDto);
        verify(eventService,times(1)).createEvent(createEventRequestDto);
    }

    @Test
    public void createEvent_ZeroMethodCalls() throws Exception {
        Mockito.doNothing().when(eventService).createEvent(createEventRequestDto);
        verify(eventService,times(0)).createEvent(createEventRequestDto);
    }
}
