package com.app.calendar.service.serviceImpl;

import com.app.calendar.Constants;
import com.app.calendar.model.EventRoomModel;
import com.app.calendar.repository.EventRoomRepository;
import com.app.calendar.service.EventRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EventRoomServiceImpl implements EventRoomService {

    @Autowired
    EventRoomRepository eventRoomRepository;

    @Override
    public boolean checkAvailability(String roomId, LocalDateTime start, LocalDateTime end) {

        String[] rooms =  Constants.allRooms;

        boolean validRoomId = Arrays.stream(rooms).anyMatch(s-> s.equalsIgnoreCase(roomId));

        if(!validRoomId){
            return false;
        }

        List<EventRoomModel> eventRoomModelList = eventRoomRepository.checkRoomAvailability(roomId,start,end);

        if(eventRoomModelList.isEmpty()){
            return true;
        }

        return false;
    }

    public void addEventRoom(Long eventId, String roomId,LocalDateTime start, LocalDateTime stop){
        EventRoomModel eventRoomModel = new EventRoomModel();
        eventRoomModel.setEventId(eventId);
        eventRoomModel.setRoomId(roomId);
        eventRoomModel.setStartTime(start);
        eventRoomModel.setEndTime(stop);
        eventRoomRepository.save(eventRoomModel);
    }

    public String getEventRoom(Long eventId){
        return eventRoomRepository.getEventRoomByEventId(eventId);
    }

    public void updateEventRoom(Long eventId, String roomId,LocalDateTime start, LocalDateTime stop){
        eventRoomRepository.updateRoomByEventId(eventId,roomId,start,stop);
    }
}
