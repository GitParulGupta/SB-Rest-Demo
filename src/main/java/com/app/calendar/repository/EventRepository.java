package com.app.calendar.repository;

import com.app.calendar.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventModel,String> {
}
