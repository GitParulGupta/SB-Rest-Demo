package com.app.calendar.service;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;

public interface EventService {

    public void createEvent(CreateEventRequestDto event) throws Exception;

    public GetEventResponseDto getEvent(Long eventId);

    public void updateEvent(CreateEventRequestDto event, Long eventId) throws Exception;

}
