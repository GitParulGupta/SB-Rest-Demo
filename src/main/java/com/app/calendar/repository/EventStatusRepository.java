package com.app.calendar.repository;

import com.app.calendar.model.EventStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStatusRepository extends JpaRepository<EventStatusModel,String> {
}
