package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventDAOImpTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void testFindAll() throws Exception {

        Event firstExpected = new Event();
        firstExpected.set_id(15L);
        Event secondExpected = new Event();
        firstExpected.set_id(16L);
        List<Event> expList = Arrays.asList(firstExpected,secondExpected);
        EventDAO eventDAO = new EventDAOImp(mongoTemplate);
        when(eventDAO.findAll()).thenReturn(expList);
        List<Event> expected = eventDAO.findAll();
        verify(mongoTemplate, times(1)).findAll(Event.class);

  }

    @Test
    public void testAdd() throws Exception {

        Event expectedEvent = new Event();
        expectedEvent.set_id(20L);
        EventDAO eventDAO = new EventDAOImp(mongoTemplate);
        eventDAO.add(expectedEvent);
        when(mongoTemplate.findById(expectedEvent.get_id(), Event.class)).thenReturn(expectedEvent);
        Event result = mongoTemplate.findById(expectedEvent.get_id(), Event.class);
        Assert.assertEquals(expectedEvent, result);
        verify(mongoTemplate, times(1)).save(expectedEvent);

    }

    @Test
    public void testDelete() throws Exception {

        Event inputEvent = new Event();
        inputEvent.set_id(13L);
        Event expectedEvent = null;
        EventDAO eventDAO = new EventDAOImp(mongoTemplate);
        eventDAO.add(inputEvent);
        eventDAO.delete(inputEvent.get_id());
        when(mongoTemplate.findById(inputEvent.get_id(), Event.class)).thenReturn(expectedEvent);
        Event result = mongoTemplate.findById(inputEvent.get_id(), Event.class);
        Assert.assertEquals(expectedEvent, result);

    }

    @Test
    public void testFindEventForPerson() throws Exception {

        Event expectedEvent = new Event();
        Person person = new Person();
        person.setId(11L);
        expectedEvent.setAttenders(Arrays.asList(person));
        expectedEvent.set_id(10L);
        expectedEvent.setDateTo(new Date());
        expectedEvent.setDateFrom(new Date());
        List<Event> expList = Arrays.asList(expectedEvent);
        EventDAO eventDAO = new EventDAOImp(mongoTemplate);
        when(eventDAO.findEventForPerson(person.getId(), expectedEvent.getDateFrom(), expectedEvent.getDateTo())).thenReturn(expList);
        List<Event> result = eventDAO.findEventForPerson(person.getId(), expectedEvent.getDateFrom(), expectedEvent.getDateTo());
        Assert.assertEquals(expList, result);

    }
}