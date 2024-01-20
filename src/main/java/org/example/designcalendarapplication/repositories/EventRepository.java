package org.example.designcalendarapplication.repositories;

import org.example.designcalendarapplication.models.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public List<Event> getAllEvents() {
        return this.events;
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public void updateEvent(Event event) {
        for (Event e : this.events) {
            if (e.getTitle().equals(event.getTitle())) {
                e.setStart(event.getStart());
                e.setEnd(event.getEnd());
                e.setLocation(event.getLocation());
                e.setOwner(event.getOwner());
                e.setAttendees(event.getAttendees());
                e.setStatus(event.getStatus());
            }
        }
    }

    public Event getEventByName(String title) {
        for (Event e : this.events) {
            if (e.getTitle().equals(title)) {
                return e;
            }
        }
        return null;
    }
}
