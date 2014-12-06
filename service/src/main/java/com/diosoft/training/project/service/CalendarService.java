package com.diosoft.training.project.service;


import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */
//local code review (vtegza): try to create service and web application, that would communicate over rmi @ 11/16/2014
//local code review (vtegza): interface should be isolated from3rd party libraries, ex: Event class is depended on springframework.data, client should not know about such dependency @ 12/6/2014
public interface CalendarService {

    List<Event> findAllEvents();

    void addEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);

    List<Event> findEventsForPerson(Long id, Date start, Date end);

    List<Person> findAllAttenders();

    void addPerson(Person person);

    void deletePerson(Person person);

}