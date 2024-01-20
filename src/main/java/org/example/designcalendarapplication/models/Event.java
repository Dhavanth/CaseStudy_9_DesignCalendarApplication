package org.example.designcalendarapplication.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Event {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String owner;
    private List<String> attendees;
    private EventStatus status;
    private EventType eventType;

    public Event(String title, LocalDateTime start, LocalDateTime end,
                 String location, List<String> attendees, String owner, EventType eventType) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.location = location;
        this.owner = owner;
        this.attendees = attendees;
        this.status = EventStatus.NEUTRAL;
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", location='" + location + '\'' +
                ", owner='" + owner + '\'' +
                ", attendees=" + attendees +
                ", status=" + status +
                ", eventType=" + eventType +
                '}';
    }
}
