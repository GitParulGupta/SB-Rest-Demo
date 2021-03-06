package com.app.calendar.repository;

import com.app.calendar.model.EventRoomModel;
import com.app.calendar.model.EventUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventUserRepository extends JpaRepository<EventUserModel,String> {

    @Query(value = "SELECT EU.USER_ID FROM EVENT_USER EU WHERE EU.EVENT_ID = ?1",nativeQuery = true)
    public List<String> getEventUserById(Long eventId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM EVENT_USER EU WHERE EU.EVENT_ID = ?1",nativeQuery = true)
    public void deleteEventUserById(Long evenId);
}
