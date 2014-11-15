package com.diosoft.training.project.service;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.persistence.repository.EventDAO;
import com.diosoft.training.project.persistence.repository.PersonDAO;
import com.diosoft.training.project.persistence.repository.PersonDAOImlp;
import com.diosoft.training.project.persistence.repository.XMLProcessor;
import com.diosoft.training.project.persistence.sequence.SequenceDAO;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CalendarServiceImp implements CalendarService, InitializingBean {

    private EventDAO eventDAO;

    private SequenceDAO sequenceDAO;

    private PersonDAO personDAO;

    private XMLProcessor xmlProcessor;

    public CalendarServiceImp() {
    }

    @Override
    public List<Event> findAllEvents() {
        return eventDAO.findAll();
    }

    @Override
    public void addEvent(Event event) {

//       event.set_id(sequenceDAO.getNextSequenceId("events"));
        eventDAO.add(event);
        xmlProcessor.add(event);
    }

    @Override
    public void updateEvent(Event event) {

        addEvent(event);
        xmlProcessor.add(event);

    }

    @Override
    public void deleteEvent(Event event) {

        eventDAO.delete(event.get_id());
        xmlProcessor.remove(event);

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

//        person.setId(sequenceDAO.getNextSequenceId("person"));
        personDAO.add(person);

    }

    @Override
    public void deletePerson(Person person) {

        personDAO.delete(person.getId());

    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public void setPersonDAO(PersonDAOImlp personDAO) {
        this.personDAO = personDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setSequenceDAO(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    public SequenceDAO getSequenceDAO() {
        return sequenceDAO;
    }

    public void setXmlProcessor(XMLProcessor xmlProcessor) {
        this.xmlProcessor = xmlProcessor;
    }

    public XMLProcessor getXmlProcessor() {
        return xmlProcessor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Event> listToWriteToXml = findAllEvents();
        for (Event event : listToWriteToXml) {
            executorService.execute(new XMLProcessor("add", event));
        }
        executorService.shutdown();

    }


}
