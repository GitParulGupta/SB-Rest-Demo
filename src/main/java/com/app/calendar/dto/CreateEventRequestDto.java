package com.app.calendar.dto;

import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreateEventRequestDto {

    @NotNull
    private String eventTypeId;
    @NotNull
    private String eventTitle;
    @NotNull
    private LocalDateTime eventDateTime;
    private int eventDurationInMins;
    private String roomId;
    @NotNull
    private String ownerId;
    private List<String> eventUser;

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public int getEventDurationInMins() {
        return eventDurationInMins;
    }

    public void setEventDurationInMins(int eventDurationInMins) {
        this.eventDurationInMins = eventDurationInMins;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getEventUser() {
        return eventUser;
    }

    public void setEventUser(List<String> eventUser) {
        this.eventUser = eventUser;
    }

    public CreateEventRequestDto(String eventTypeId, String eventTitle, LocalDateTime eventDateTime, int eventDurationInMins, String roomId, String ownerId, List<String> eventUser) {
        this.eventTypeId = eventTypeId;
        this.eventTitle = eventTitle;
        this.eventDateTime = eventDateTime;
        this.eventDurationInMins = eventDurationInMins;
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.eventUser = eventUser;
    }
}
