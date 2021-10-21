package com.app.calendar.service;

import com.app.calendar.dto.CreateEventRequestDto;

public interface EventService {

    public void createEvent(CreateEventRequestDto event) throws Exception;

}
