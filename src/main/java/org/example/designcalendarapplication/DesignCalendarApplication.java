package org.example.designcalendarapplication;

import org.example.designcalendarapplication.controllers.CalendarController;
import org.example.designcalendarapplication.models.Event;
import org.example.designcalendarapplication.models.EventType;
import org.example.designcalendarapplication.repositories.EventRepository;
import org.example.designcalendarapplication.services.CalendarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DesignCalendarApplication {

    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        CalendarService calendarService = new CalendarService(eventRepository);
        CalendarController calendarController = new CalendarController(calendarService);

        List<String> attendees = new ArrayList<>();
        attendees.add("User1");
        attendees.add("User2");
        attendees.add("User3");

        LocalDateTime start1 = LocalDateTime.of(2021, 1, 1, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2021, 1, 1, 11, 0);

        Event event1 = new Event("Event1", start1, end1, "Location1", attendees,"Owner1", EventType.MEETING);
        calendarController.createEvent(event1);



        LocalDateTime start2 = LocalDateTime.of(2021, 1, 1, 12, 0);
        LocalDateTime end2 = LocalDateTime.of(2021, 1, 1, 13, 0);

        Event event2 = new Event("Event2", start2, end2, "Location2", attendees,"Owner2", EventType.CONFERENCE);
        calendarController.createEvent(event2);

        calendarController.getEventDetails("Event1");
        calendarController.getEventDetails("Event2");

        List<LocalDateTime> freeSlotAvailableForListOfUsers = calendarController.findCommonFreeSlot(attendees);
        System.out.println("Free slots for given list of users: ");
        for(int i = 0; i < freeSlotAvailableForListOfUsers.size(); i += 2){
            System.out.println(freeSlotAvailableForListOfUsers.get(i).toString() + " to " + freeSlotAvailableForListOfUsers.get(i+1).toString());
            System.out.println();
        }
        System.out.println("-----------------------------------");

        List<LocalDateTime> freeSlotsForAUser =  calendarController.findFreeSlotForAUser("User1");
        System.out.println("Free slots for given user: ");
        for(int i = 0; i < freeSlotsForAUser.size(); i += 2){
            System.out.println(freeSlotsForAUser.get(i).toString() + " to " + freeSlotsForAUser.get(i+1).toString());
            System.out.println();
        }
        System.out.println("-----------------------------------");

        List<Event> eventsOfAUser = calendarController.getCalendarForUser("User2");
        System.out.println("Events of given user: ");
        for(Event event : eventsOfAUser) {
            System.out.println(event.toString());
        }
        System.out.println("-----------------------------------");
        System.out.println();


        calendarController.deleteEvent(event2);
        System.out.println("-----------------------------------");
        System.out.println();

        SpringApplication.run(DesignCalendarApplication.class, args);
    }

}
