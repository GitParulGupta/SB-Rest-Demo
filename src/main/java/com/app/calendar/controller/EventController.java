package com.app.calendar.controller;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.exception.EventNotFoundException;
import com.app.calendar.model.EventModel;
import com.app.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(path= "/create", consumes = "application/json")
    public void createEvent(@RequestBody CreateEventRequestDto event) throws Exception {
        eventService.createEvent(event);
    }

    @GetMapping("/eventDetail/{eventId}")
    public GetEventResponseDto getEventDetails(@PathVariable Long eventId){
        GetEventResponseDto getEventResponseDto = eventService.getEvent(eventId);
        if(ObjectUtils.isEmpty(getEventResponseDto)){
            throw  new EventNotFoundException("Event id:"+eventId+" not found. Enter valid eventId");
        }
        return getEventResponseDto;
    }

    @PatchMapping("/update/{eventId}")
    public void updateEvent(@RequestBody CreateEventRequestDto createEventRequestDto, @PathVariable Long eventId) throws Exception {
        eventService.updateEvent(createEventRequestDto,eventId);
    }
}
