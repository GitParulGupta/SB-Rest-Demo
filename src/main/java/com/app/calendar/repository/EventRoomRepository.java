package com.app.calendar.repository;

import com.app.calendar.model.EventRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRoomRepository extends JpaRepository<EventRoomModel,String> {

    @Query(value = "SELECT * FROM EVENT_ROOM ER WHERE ER.ROOM_ID = ?1 AND ER.START_TIME>=?2 AND ER.END_TIME<=?3",nativeQuery = true)
    public List<EventRoomModel> checkRoomAvailability(String roomId, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT ER.ROOM_ID FROM EVENT_ROOM ER WHERE ER.EVENT_ID = ?1",nativeQuery = true)
    public String getEventRoomByEventId(Long eventId);

    @Query(value = "UPDATE EVENT_ROOM ER SET ER.ROOM_ID = ?1 AND ER.START_TIME>=?2 AND ER.END_TIME<=?3 WHERE ER.EVENT_ID = ?1",nativeQuery = true)
    public void updateRoomByEventId(Long eventId, String roomId, LocalDateTime start, LocalDateTime end);
}
