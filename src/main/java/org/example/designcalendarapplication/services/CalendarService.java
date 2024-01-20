package org.example.designcalendarapplication.services;

import org.example.designcalendarapplication.models.Event;
import org.example.designcalendarapplication.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private EventRepository eventRepository;


    @Autowired
    public CalendarService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createEvent(Event event) {
        eventRepository.addEvent(event);
        System.out.println("Event: "+ event.getTitle() + " created successfully!");
        System.out.println();
    }

    public void updateEvent(Event event) {
        eventRepository.updateEvent(event);
        System.out.println("Event: "+ event.getTitle() + " updated successfully!");
        System.out.println();
        // add user defined exception for event not found
    }

    public void deleteEvent(Event event) {
        eventRepository.removeEvent(event);
        System.out.println("Event deleted successfully!");
        System.out.println();
        // add user defined exception for event not found

    }

    public void getEventDetails(String titlename) {
        Event event = eventRepository.getEventByName(titlename);
        System.out.println("Event details:");
        System.out.println(event.toString());
        System.out.println();
        // add user defined exception for event not found
    }

    public List<Event> getCalendarForUser(String userName) {

        return eventRepository.getAllEvents().stream()
                .filter(e -> e.getAttendees().contains(userName))
                .collect(Collectors.toList());
        // add user defined exception for user not found

    }

    public List<Event> getSortedEventsByStartDateAndTime(){
        return eventRepository.getAllEvents().stream()
                .sorted((e1, e2) -> e1.getStart().compareTo(e2.getStart()))
                .collect(Collectors.toList());
    }


    public List<LocalDateTime> findCommonFreeSlot(List<String> userNames) {
        List<Event> sortedEvents = getSortedEventsByStartDateAndTime();

        List<LocalDateTime> busySlots = new ArrayList<>();

        for(Event event: sortedEvents){
            if(event.getAttendees().stream().anyMatch(userNames::contains)){
                busySlots.add(event.getStart());
                busySlots.add(event.getEnd());
            }
        }

        List<LocalDateTime> freeSlots = new ArrayList<>();

        for(int i = 0; i < busySlots.size(); i += 2){
            LocalDateTime currentEnd = (i + 1 < busySlots.size()) ? busySlots.get(i + 1) : null;
            LocalDateTime nextStart = (i + 2 < busySlots.size()) ? busySlots.get(i + 2) : null;

            if(currentEnd != null && nextStart != null){
                Duration duration = Duration.between(currentEnd, nextStart);
                if(duration.toMinutes() >= 30){
                    freeSlots.add(currentEnd);
                    freeSlots.add(nextStart);
                }
            }
        }

        return freeSlots;
    }

    public List<LocalDateTime> findFreeSlotForAUser(String userName){

        List<Event> sortedEvents = getSortedEventsByStartDateAndTime();

        List<LocalDateTime> busySlots = new ArrayList<>();

        for(Event event: sortedEvents){
            if(event.getAttendees().stream().anyMatch(userName::equals)){
                busySlots.add(event.getStart());
                busySlots.add(event.getEnd());
            }
        }

        List<LocalDateTime> freeSlots = new ArrayList<>();

        for(int i = 0; i < busySlots.size(); i += 2){
            LocalDateTime currentEnd = (i + 1 < busySlots.size()) ? busySlots.get(i + 1) : null;
            LocalDateTime nextStart = (i + 2 < busySlots.size()) ? busySlots.get(i + 2) : null;

            if(currentEnd != null && nextStart != null){
                Duration duration = Duration.between(currentEnd, nextStart);
                if(duration.toMinutes() >= 30){
                    freeSlots.add(currentEnd);
                    freeSlots.add(nextStart);
                }
            }
        }
        return freeSlots;
    }
}

