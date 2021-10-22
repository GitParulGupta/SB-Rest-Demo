package com.app.calendar.controller;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.model.EventModel;
import com.app.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public GetEventResponseDto getEventDetails(@PathVariable int eventId){
        return eventService.getEvent(eventId);
    }

}
