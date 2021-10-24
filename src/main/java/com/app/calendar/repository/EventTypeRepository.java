package com.app.calendar.repository;

import com.app.calendar.model.EventTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventTypeRepository extends JpaRepository<EventTypeModel,String> {

    @Query(value = "SELECT ET.EVENT_TYPE_NAME FROM EVENT_TYPE ET WHERE ET.EVENT_TYPE_ID = ?1",nativeQuery = true)
    public String getEventTypeById(String eventTypeId);

}
