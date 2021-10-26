package com.app.calendar.service;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;

public interface EventService {

    public GetEventResponseDto createEvent(CreateEventRequestDto event) throws Exception;

    public GetEventResponseDto getEvent(Long eventId);

    public GetEventResponseDto updateEvent(CreateEventRequestDto event, Long eventId) ;

}
