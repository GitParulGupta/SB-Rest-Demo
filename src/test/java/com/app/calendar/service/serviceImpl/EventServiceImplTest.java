package com.app.calendar.service.serviceImpl;

import com.app.calendar.dto.CreateEventRequestDto;
import com.app.calendar.repository.EventRepository;
import com.app.calendar.repository.EventTypeRepository;
import com.app.calendar.service.EventRoomService;
import com.app.calendar.service.EventService;
import com.app.calendar.service.EventUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @Mock
    EventRepository eventRepository;
    @Mock
    EventRoomService eventRoomService;
    @Mock
    EventUserService eventUserService;
    @Mock
    EventTypeRepository eventTypeRepository;

    @InjectMocks
    EventService eventService;

    String[] user = {"U3","U2"};
    List<String> userList = Arrays.asList(user);
    CreateEventRequestDto createMeetingEventRequestDto = new CreateEventRequestDto("M","Meeting 1", LocalDateTime.of(2021,10,26,2,30,0),30,"R1","U2",userList);

}
