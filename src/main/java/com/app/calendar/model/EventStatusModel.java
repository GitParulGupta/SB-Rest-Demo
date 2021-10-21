package com.app.calendar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="event_status")
public class EventStatusModel {

    @Id
    @Column(name="event_status_id")
    private String eventStatusId;

    @Column(name="event_status_name")
    private String eventStatusName;

    public String getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(String eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    public String getEventStatusName() {
        return eventStatusName;
    }

    public void setEventStatusName(String eventStatusName) {
        this.eventStatusName = eventStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventStatusModel eventType = (EventStatusModel) o;
        return Objects.equals(eventStatusId, eventType.eventStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventStatusId);
    }

}
