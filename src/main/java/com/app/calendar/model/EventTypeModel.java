package com.app.calendar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="event_type")
public class EventTypeModel {

    @Id
    @Column(name="event_type_id")
    private String eventTypeId;

    @Column(name="event_type_name")
    private String eventTypeName;

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventTypeModel eventType = (EventTypeModel) o;
        return Objects.equals(eventTypeId, eventType.eventTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTypeId);
    }

    @Override
    public String toString() {
        return "EventType{" +
                "eventTypeId='" + eventTypeId + '\'' +
                ", eventTypeName='" + eventTypeName + '\'' +
                '}';
    }
}
