package org.example.designcalendarapplication.controllers;

import org.example.designcalendarapplication.models.Event;
import org.example.designcalendarapplication.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CalendarController {

    private CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    public void createEvent(Event event) {
        calendarService.createEvent(event);
    }

    public void updateEvent(Event event) {
        calendarService.updateEvent(event);
    }

    public void deleteEvent(Event event) {
        calendarService.deleteEvent(event);
    }

    public void getEventDetails(String titlename) {
        calendarService.getEventDetails(titlename);
    }

    public List<Event> getCalendarForUser(String userName) {
        return calendarService.getCalendarForUser(userName);
    }

    public List<LocalDateTime> findCommonFreeSlot(List<String> users) {
        return calendarService.findCommonFreeSlot(users);
    }

    public List<LocalDateTime> findFreeSlotForAUser(String user) {
        return calendarService.findFreeSlotForAUser(user);
    }


}
