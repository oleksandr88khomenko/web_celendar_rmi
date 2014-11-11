package com.diosoft.training.project.service;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */

import com.diosoft.training.project.persistence.PersonDAO;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.persistence.sequence.SequenceDAO;
import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalendarServiceImp implements CalendarService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    PersonDAO personDAO;

    public CalendarServiceImp() {

    }

    @Override
    public List<Event> findAllEvents() {
        return eventDAO.findAll();
    }

    @Override
    public void addEvent(Event event) {
        event.set_id(sequenceDAO.getNextSequenceId("events"));
        eventDAO.add(event);
    }

    @Override
    public void updateEvent(Event event) {
        addEvent(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventDAO.delete(event.get_id());

    }

    @Override
    public List<Event> findEventsForPerson(Long id, Date start, Date end) {
       return eventDAO.findEventForPerson(id, start, end);
    }

    @Override
    public List<Person> findAllAttenders() {
         return personDAO.findAll();
    }

    @Override
    public void addPerson(Person person) {
        person.setId(sequenceDAO.getNextSequenceId("person"));
        personDAO.add(person);
    }

    @Override
    public void deletePerson(Person person) {
        personDAO.delete(person.getId());

    }
}
