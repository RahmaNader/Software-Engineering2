package com.phase2.webAPI.repositories;

import com.phase2.webAPI.entity.Event;
import com.phase2.webAPI.entity.EventName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByEventName(EventName eventName);

    List<Event> findAllByTime(LocalDate date);

    List<Event> findAllByUsername(String username);

    List<Event> findAllByDrivername(String drivername);

    List<Event> findAllByRideId(int id);

}
