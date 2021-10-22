package com.app.calendar.service.serviceImpl;

import com.app.calendar.Constants;
import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.exception.EventNotFoundException;
import com.app.calendar.model.EventModel;
import com.app.calendar.model.EventTypeModel;
import com.app.calendar.repository.EventRepository;
import com.app.calendar.repository.EventTypeRepository;
import com.app.calendar.service.EventRoomService;
import com.app.calendar.service.EventService;
import com.app.calendar.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventRoomService eventRoomService;
    @Autowired
    EventUserService eventUserService;
    @Autowired
    EventTypeRepository eventTypeRepository;

    @Override
    public void createEvent(CreateEventRequestDto createEventRequestDto) throws Exception {

        String eventType = createEventRequestDto.getEventTypeId();

        switch(eventType){
            case "M":
                createMeetingEvent(createEventRequestDto);
                break;
            case "B":
                createFulldayEvent(createEventRequestDto);
                break;
            case "H":
                createFulldayEvent(createEventRequestDto);
                break;
            default:
                throw new Exception();
        }
    }

    public void createFulldayEvent(CreateEventRequestDto createEventRequestDto){
        LocalDateTime start = createEventRequestDto.getEventDateTime();
        LocalDateTime end = start.plusMinutes(Constants.fullDayEventDurationMins);

        EventModel eventModel = new EventModel(createEventRequestDto.getEventTypeId(), createEventRequestDto.getEventTitle(), start,end, createEventRequestDto.getOwnerId());

        eventRepository.save(eventModel);
    }

    public void createMeetingEvent(CreateEventRequestDto createEventRequestDto) throws Exception {

        LocalDateTime startLocalDateTime = createEventRequestDto.getEventDateTime();

        LocalDateTime endLocalDateTime = startLocalDateTime.plusMinutes(createEventRequestDto.getEventDurationInMins());
        EventModel eventModel = new EventModel(createEventRequestDto.getEventTypeId(), createEventRequestDto.getEventTitle(), startLocalDateTime,endLocalDateTime, createEventRequestDto.getOwnerId());


        boolean isAvailable = eventRoomService.checkAvailability(createEventRequestDto.getRoomId(),startLocalDateTime, endLocalDateTime);

        if(isAvailable){
            EventModel eventModelCreated = eventRepository.save(eventModel);
            Long eventId = eventModelCreated.getEventId();
            List<String> userIds = createEventRequestDto.getEventUser();
            eventUserService.addEventUser(userIds,eventId,startLocalDateTime,endLocalDateTime);
            eventRoomService.addEventRoom(eventId,createEventRequestDto.getRoomId(),startLocalDateTime,endLocalDateTime);
        }else{
            throw new Exception();
        }
    }

    @Override
    public GetEventResponseDto getEvent(int eventId) {
        EventModel eventModel = eventRepository.getById(Long.valueOf(eventId));

        if(ObjectUtils.isEmpty(eventModel)){
            throw  new EventNotFoundException("Event id:"+eventId+" not found. Enter valid eventId");
        }

        Optional<EventTypeModel> eventTypeModel = eventTypeRepository.findById(eventModel.getEventTypeId());

        List<String> eventUserModelList = eventUserService.getEventUser(eventId);
        String eventRoom = eventRoomService.getEventRoom(eventId);
        GetEventResponseDto getEventResponseDto = new GetEventResponseDto(eventModel.getEventId(),eventTypeModel.get().getEventTypeName(),eventModel.getEventTitle(),eventModel.getStartTime(),eventModel.getEndTime(),eventRoom,eventModel.getOwnerId(),eventUserModelList);
        return getEventResponseDto;
    }
}
