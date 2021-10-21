package com.app.calendar.controller;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(path= "/create", consumes = "application/json")
    public void createEvent(@RequestBody CreateEventRequestDto event) throws Exception {
        eventService.createEvent(event);
    }

}
