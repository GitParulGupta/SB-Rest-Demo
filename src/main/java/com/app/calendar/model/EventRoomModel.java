package com.app.calendar.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="event_room")
public class EventRoomModel {

    @Id
    @Column(name="event_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventRoomId;

    @Column(name="event_id")
    private Long eventId;

    @Column(name="room_id")
    private String roomId;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    public Long getEventId() {
        return eventId;
    }

    public Long getEventRoomId() {
        return eventRoomId;
    }

    public void setEventRoomId(Long eventRoomId) {
        this.eventRoomId = eventRoomId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRoomModel that = (EventRoomModel) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId,startTime, endTime);
    }


}
