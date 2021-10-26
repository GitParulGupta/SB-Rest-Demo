package com.app.calendar.controller;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.exception.InvalidEventTypeException;
import com.app.calendar.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    EventService eventService;
    String[] user = {"U3","U2"};
    List<String> userList = Arrays.asList(user);
    GetEventResponseDto eventResponseDto = new GetEventResponseDto(1L,"Meeting","Meeting 1", LocalDateTime.of(2021,10,26,2,30,0),LocalDateTime.of(2021,10,26,3,0,0),"R1","U1", userList);

    Long eventId = 1L;

    @Test
    public void getEvent_success() throws Exception {
        when(eventService.getEvent(eventResponseDto.getEventId())).thenReturn(eventResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/event/eventDetail/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventTitle").value("Meeting 1"));
    }

    @Test
    public void getEvent_failure() throws Exception {
        when(eventService.getEvent(eventResponseDto.getEventId())).thenReturn(eventResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/event/eventDetail/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto("M","Meeting 1",LocalDateTime.of(2021,10,26,2,30,0),30,"R1","U1",userList);

    @Test
    public void createEvent_success() throws Exception {
        when(eventService.createEvent(Mockito.any(CreateEventRequestDto.class))).thenReturn(eventResponseDto);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/event")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(createEventRequestDto));

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void createEvent_failure() throws Exception{
        CreateEventRequestDto createEventRequestFailure = new CreateEventRequestDto("K","Meeting 1",LocalDateTime.of(2021,10,26,2,30,0),30,"R1","U1",userList);
        when(eventService.createEvent(Mockito.any(CreateEventRequestDto.class))).thenThrow(new InvalidEventTypeException("Invalid Event"));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/event")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(createEventRequestFailure));

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    CreateEventRequestDto createEventRequestDtoToUpdate = new CreateEventRequestDto("M","Meeting 1",LocalDateTime.of(2021,10,26,3,30,0),30,"R1","U1",userList);
    GetEventResponseDto eventResponseDtoUpdated = new GetEventResponseDto(1L,"Meeting","Meeting 1", LocalDateTime.of(2021,10,26,3,30,0),LocalDateTime.of(2021,10,26,4,0,0),"R1","U1", userList);

    @Test
    public  void updateEvent_success() throws Exception {
        when(eventService.updateEvent(createEventRequestDtoToUpdate,eventId)).thenReturn(eventResponseDtoUpdated);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/event/update/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(createEventRequestDtoToUpdate));

        mockMvc.perform(builder).andExpect(status().isOk());
    }

}
