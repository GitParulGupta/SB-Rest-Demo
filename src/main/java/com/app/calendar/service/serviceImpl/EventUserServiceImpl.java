package com.app.calendar.service.serviceImpl;

import com.app.calendar.Constants;
import com.app.calendar.model.EventUserModel;
import com.app.calendar.repository.EventUserRepository;
import com.app.calendar.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventUserServiceImpl implements EventUserService {

    @Autowired
    EventUserRepository eventUserRepository;

    @Override
    public void addEventUser(List<String> userIds, Long eventId, LocalDateTime start, LocalDateTime end) {

        userIds.forEach(user ->{
            EventUserModel eventUserModel = new EventUserModel();
            eventUserModel.setEventId(eventId);
            eventUserModel.setUserId(user);
            eventUserModel.setStartTime(start);
            eventUserModel.setEndTime(end);
            eventUserModel.setUserStatus(Constants.DEFAULT_STATUS);
           eventUserRepository.save(eventUserModel);
        });
    }
}
