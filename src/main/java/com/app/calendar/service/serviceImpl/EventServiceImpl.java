package com.app.calendar.service.serviceImpl;

import com.app.calendar.Constants;
import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.dto.GetEventResponseDto;
import com.app.calendar.exception.EventNotFoundException;
import com.app.calendar.exception.InvalidEventUpdateException;
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
import java.util.Collections;
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
                createFullDayEvent(createEventRequestDto);
                break;
            case "H":
                createFullDayEvent(createEventRequestDto);
                break;
            default:
                throw new Exception();
        }
    }

    public void createFullDayEvent(CreateEventRequestDto createEventRequestDto){
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
    public GetEventResponseDto getEvent(Long eventId) {
        Optional<EventModel> eventModelObj = eventRepository.findById(eventId);

        if(!eventModelObj.isPresent()){
            return null;
        }
        EventModel eventModel = eventModelObj.get();
        String eventTypeName = eventTypeRepository.getEventTypeById(eventModel.getEventTypeId());

        List<String> eventUserModelList = eventUserService.getEventUser((long) eventId);
        String eventRoom = eventRoomService.getEventRoom((long) eventId);
        return new GetEventResponseDto(eventModel.getEventId(),eventTypeName,eventModel.getEventTitle(),eventModel.getStartTime(),eventModel.getEndTime(),eventRoom,eventModel.getOwnerId(),eventUserModelList);
    }

    @Override
    public void updateEvent(CreateEventRequestDto event, Long eventId) throws Exception {

        Optional<EventModel> eventModelObj = eventRepository.findById(eventId);

        if(!eventModelObj.isPresent()){
            throw  new EventNotFoundException("Event id:"+eventId+" not found. Enter valid eventId");
        }
        EventModel eventModel = eventModelObj.get();

        if(!eventModel.getEventTypeId().equalsIgnoreCase(event.getEventTypeId())){
            String existingEventType = eventTypeRepository.getById(eventModel.getEventTypeId()).getEventTypeName();
            String newEventType = eventTypeRepository.getById(event.getEventTypeId()).getEventTypeName();
            throw new InvalidEventUpdateException("Event of type:"+existingEventType+" cannot be converted to:"+newEventType);
        }

        if(!event.getEventTitle().isBlank()){
            eventModel.setEventTitle(event.getEventTitle());
        }

        if(eventModel.getEventTypeId().equalsIgnoreCase("M")){
            LocalDateTime startLocalDateTime = event.getEventDateTime();
            LocalDateTime endLocalDateTime = startLocalDateTime.plusMinutes(event.getEventDurationInMins());
            eventModel.setStartTime(startLocalDateTime);
            eventModel.setEndTime(endLocalDateTime);
        }else{
            LocalDateTime startLocalDateTime = event.getEventDateTime();
            LocalDateTime endLocalDateTime = startLocalDateTime.plusMinutes(Constants.fullDayEventDurationMins);
            eventModel.setStartTime(startLocalDateTime);
            eventModel.setEndTime(endLocalDateTime);
        }

        if(!eventModel.getOwnerId().isBlank()){
            eventModel.setOwnerId(event.getOwnerId());
        }

        String eventRoom = eventRoomService.getEventRoom(eventId);
        if(!eventRoom.equalsIgnoreCase(event.getRoomId())){
            boolean isAvailable = eventRoomService.checkAvailability(eventRoom,eventModel.getStartTime(), eventModel.getEndTime());
            if(isAvailable){
                eventRoomService.updateEventRoom(eventId,event.getRoomId(),eventModel.getStartTime(), eventModel.getEndTime());
            }else{
                throw new Exception();
            }
        }

        eventUserService.deleteEventUser(eventId);

        List<String> userIds = event.getEventUser();
        eventUserService.addEventUser(userIds,eventId,eventModel.getStartTime(),eventModel.getEndTime());

        eventRepository.save(eventModel);
    }
}
