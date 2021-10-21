package com.app.calendar.repository;

import com.app.calendar.model.EventTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventTypeModel,String> {
}
