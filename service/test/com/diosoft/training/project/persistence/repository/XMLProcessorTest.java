package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class XMLProcessorTest {

    private XMLProcessor xmlProcessor;

    private File file;

    @After
    public void tearDown() throws Exception {
        file.delete();
    }

    @Test
    public void testAdd() throws Exception {

        Event input = new Event();

        input.set_id(20L);
        input.setDateFrom(new Date());
        input.setDateTo(new Date());
        Person sasha = new Person();
        Person sergii = new Person();
        List<Person> attenders = Arrays.asList(sasha, sergii);
        input.setAttenders(attenders);
        input.setDescription("vacation");
        input.setTitle("vacation");

        XMLProcessor xmlProcessor = new XMLProcessor();
        xmlProcessor.add(input);
        file = new File("Event" + input.get_id() + ".xml");
        Assert.assertEquals(file.exists(), true);

    }

    @Test
    public void testRemove() throws Exception {

        Event input = new Event();

        input.set_id(20L);
        input.setDateFrom(new Date());
        input.setDateTo(new Date());
        Person sasha = new Person();
        Person sergii = new Person();
        List<Person> attenders = Arrays.asList(sasha, sergii);
        input.setAttenders(attenders);
        input.setDescription("vacation");
        input.setTitle("vacation");

        xmlProcessor = new XMLProcessor();
        xmlProcessor.add(input);
        xmlProcessor.remove(input);
        //local code review (vtegza): this is more like integration test, consider this @ 12/6/2014
        file = new File("Event" + input.get_id() + ".xml");
        Assert.assertEquals(file.exists(), false);


    }
}