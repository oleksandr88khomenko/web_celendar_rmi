package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonDAOImlpTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void testFindAll() throws Exception {

        PersonDAO personDAO = new PersonDAOImlp(mongoTemplate);
        Person person = new Person();
        person.setId(11L);
        List<Person> expList = Arrays.asList(person);
        when(mongoTemplate.findAll(Person.class)).thenReturn(expList);
        List<Person> result = personDAO.findAll();
        Assert.assertEquals(expList, result);
        verify(mongoTemplate).findAll(Person.class);

    }

    @Test
    public void testAdd() throws Exception {

        Person person = new Person();
        person.setId(11L);
        PersonDAO personDAO = new PersonDAOImlp(mongoTemplate);
        personDAO.add(person);
        verify(mongoTemplate).save(person);

    }

    @Test
    public void testDelete() throws Exception {
        //have some question about how to implement test for void method. Can you give a hint?
        Person person = new Person();
        person.setId(11L);
        Person expectedEvent = null;
        PersonDAO personDAO = new PersonDAOImlp(mongoTemplate);
        personDAO.add(person);
        personDAO.delete(person.getId());
        when(mongoTemplate.findById(person.getId(), Person.class)).thenReturn(expectedEvent);
        Person result = mongoTemplate.findById(person.getId(), Person.class);
        Assert.assertEquals(expectedEvent, result);
    }
}