package com.diosoft.training.project.web;

//import com.diosoft.training.project.web.persistence.model.Event;
//import com.diosoft.training.project.web.persistence.model.Person;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.service.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */
@Controller
public class EventController {

    @Autowired
    private CalendarService calendarService;


    @RequestMapping(method = RequestMethod.GET)
    private String home(){

        return "index";

    }

    @RequestMapping(value = "/find_all_events", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    private List<Event> findAllEvents() {
       return calendarService.findAllEvents();
    }
//    consumes="application/json"
    @RequestMapping(value = "/add_event", method = RequestMethod.GET)
    public void addEvent(@RequestBody String json) {

        Person sasha = new Person();
        sasha.setId(2L);
        sasha.setUsername("sasha");
        List<Person> list = Arrays.asList(sasha);

        Event event_ = new Event();
        event_.set_id(3L);
        event_.setDateFrom(new Date());
        event_.setDateTo(new Date());
        event_.setDescription("first event");
        event_.setTitle("event");
        event_.setAttenders(list);
        Event event2 = new Event();
        event2.set_id(2L);
        event2.setDateFrom(new Date());
        event2.setDateTo(new Date());
        event2.setDescription("first event");
        event2.setTitle("event");
        event2.setAttenders(list);

        Event event3 = new Event();
        event3.set_id(1L);
        event3.setDateFrom(new Date());
        event3.setDateTo(new Date());
        event3.setDescription("first event");
        event3.setTitle("event");
        event3.setAttenders(list);
//        try {
//            event = new ObjectMapper().readValue(json, Event.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        calendarService.addEvent(event_);

    }

    @RequestMapping(value = "/update_event")
    public void updateEvent(@RequestBody String json) {

        Event event = null;
        try {
            event = new ObjectMapper().readValue(json, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.updateEvent(event);
    }

    @RequestMapping(value = "/delete_event")
    public void deleteEvent(@RequestBody String json) {

        Event event_ = new Event();
        event_.set_id(3L);
        event_.setDateFrom(new Date());
        event_.setDateTo(new Date());
        event_.setDescription("first event");
        event_.setTitle("event");
        calendarService.deleteEvent(event_);
    }

    @RequestMapping(value = "/find_events_for_person/{id}/{start}/{end}", produces="application/json" )
    @ResponseBody
    public List<Event> findEventsForPerson(@PathVariable Long id, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {

        return calendarService.findEventsForPerson(id, start, end);

    }

    @RequestMapping(value = "/delete_person")
    public void deletePerson(@RequestBody String json) {
        Person sasha = new Person();
        sasha.setId(2L);
        sasha.setUsername("sasha");
//        Person person = null;
//        try {
//            person = new ObjectMapper().readValue(json, Person.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        calendarService.deletePerson(sasha);

    }

    @RequestMapping(value = "/add_person")
    public void addPerson(@RequestBody String json) {

        Person sasha = new Person();
        sasha.setId(2L);
        sasha.setUsername("sasha");
//        Person person = null;
//        try {
//            person = new ObjectMapper().readValue(json, Person.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        calendarService.addPerson(sasha);

    }

    @RequestMapping(value = "/find_all_attenders", produces="application/json")
    @ResponseBody
    public List<Person> findAllAttenders() {

        return calendarService.findAllAttenders();

    }

    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }
}
