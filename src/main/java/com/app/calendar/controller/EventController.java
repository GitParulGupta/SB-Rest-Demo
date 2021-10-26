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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(path= "/event", consumes = "application/json")
    public ResponseEntity<GetEventResponseDto> createEvent(@RequestBody CreateEventRequestDto event) throws Exception {
        GetEventResponseDto getEventResponseDto =  eventService.createEvent(event);
        return ResponseEntity.ok(getEventResponseDto);
    }

    @GetMapping("event/eventDetail/{eventId}")
    public GetEventResponseDto getEventDetails(@PathVariable Long eventId){
        GetEventResponseDto getEventResponseDto = eventService.getEvent(eventId);
        if(ObjectUtils.isEmpty(getEventResponseDto)){
            throw  new EventNotFoundException("Event id:"+eventId+" not found. Enter valid eventId");
        }
        return getEventResponseDto;
    }

    @PatchMapping("event/update/{eventId}")
    public ResponseEntity<GetEventResponseDto> updateEvent(@RequestBody CreateEventRequestDto createEventRequestDto, @PathVariable Long eventId) {
        GetEventResponseDto getEventResponseDto = eventService.updateEvent(createEventRequestDto,eventId);
        return ResponseEntity.ok(getEventResponseDto);
    }
}
