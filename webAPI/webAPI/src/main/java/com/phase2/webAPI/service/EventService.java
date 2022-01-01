package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Event;
import com.phase2.webAPI.entity.EventName;
import com.phase2.webAPI.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> displayEvent() {
        return eventRepository.findAll();
    }

    public List<Event> displayEventByEventName(EventName eventName) {
        return eventRepository.findAllByEventName(eventName);
    }

    public List<Event> displayEventByDate(LocalDate date) {
        return eventRepository.findAllByTime(date);
    }

    public List<Event> displayEventByUser(String username) {
        return eventRepository.findAllByUsername(username);
    }

    public List<Event> displayEventByDriver(String drivername) {
        return eventRepository.findAllByDrivername(drivername);
    }

    public List<Event> displayEventByRideID(int id) {
        return eventRepository.findAllByRideId(id);
    }

    public String addEvent(Event event) {
        eventRepository.save(event);
        return "event created";
    }
}
