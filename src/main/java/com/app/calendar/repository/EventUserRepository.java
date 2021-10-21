package com.app.calendar.repository;

import com.app.calendar.model.EventUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventUserRepository extends JpaRepository<EventUserModel,String> {
}
