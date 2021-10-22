package com.app.calendar.dto;

import com.sun.istack.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class GetEventResponseDto {

    private Long eventId;
    private String eventTypeName;
    private String eventTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;
    private String ownerId;
    private List<String> eventUser;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTypeId() {
        return eventTypeName;
    }

    public void setEventTypeId(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public GetEventResponseDto(Long eventId, String eventTypeName, String eventTitle, LocalDateTime startTime, LocalDateTime endTime, String roomId, String ownerId, List<String> eventUser) {
        this.eventId = eventId;
        this.eventTypeName = eventTypeName;
        this.eventTitle = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.eventUser = eventUser;
    }
}
