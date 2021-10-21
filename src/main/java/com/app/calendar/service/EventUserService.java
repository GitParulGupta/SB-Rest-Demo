package com.app.calendar.service;

import java.time.LocalDateTime;
import java.util.List;

public interface EventUserService {

    public void addEventUser(List<String> userIds, Long eventId, LocalDateTime start, LocalDateTime end);

}
