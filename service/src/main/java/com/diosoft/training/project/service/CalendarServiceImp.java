package com.diosoft.training.project.service;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.persistence.repository.*;
import com.diosoft.training.project.persistence.sequence.SequenceDAO;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//local code review (vtegza): create tests @ 11/16/2014
//local code review (vtegza): stick to one type of bean definition with annotations or with xml - but not both @ 11/16/2014
public class CalendarServiceImp implements CalendarService, InitializingBean {

    //local code review (vtegza): prefer constructor repository setting @ 11/16/2014
    private EventDAO eventDAO;

    private SequenceDAO sequenceDAO;

    private PersonDAO personDAO;

    private XMLProcessor xmlProcessor;

    public CalendarServiceImp() {
    }

    public CalendarServiceImp(EventDAOImp eventDAOImp, PersonDAOImlp personDAOImlp, SequenceDAO sequenceDAO) {
        this.eventDAO = eventDAOImp;
        this.sequenceDAO = sequenceDAO;
        this.personDAO = personDAOImlp;
    }

    @Override
    public List<Event> findAllEvents() {
        return eventDAO.findAll();
    }

    @Override
    public void addEvent(Event event) {

        event.set_id(sequenceDAO.getNextSequenceId("events"));
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

        person.setId(sequenceDAO.getNextSequenceId("person"));
        personDAO.add(person);

    }

    @Override
    public void deletePerson(Person person) {

        personDAO.delete(person.getId());

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
        //local code review (vtegza): there is method - awaitTermination, could be useful if you want to wait when all execution is completed @ 11/16/2014
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdownNow();

    }


}
