package com.app.calendar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EventRoomService {

    public boolean checkAvailability(String roomId, LocalDateTime start, LocalDateTime end);
    public void addEventRoom(Long eventId, String roomId,LocalDateTime start, LocalDateTime stop);
    public String getEventRoom(Long eventId);
    public void updateEventRoom(Long eventId, String roomId,LocalDateTime start, LocalDateTime stop);
}
