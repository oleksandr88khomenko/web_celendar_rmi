package com.diosoft.training.project.web;

//import com.diosoft.training.project.web.persistence.model.Event;
//import com.diosoft.training.project.web.persistence.model.Person;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.service.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */
//local code review (vtegza): add maven dependency on service @ 11/16/2014
//local code review (vtegza): clean up code @ 11/16/2014
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
        sasha.setId(4L);
        sasha.setUsername("sasha");
        List<Person> list = Arrays.asList(sasha);

        //local code review (vtegza): use fine naming @ 12/6/2014
        Event event_ = new Event();
        event_.setDateFrom(new Date(1416142089917l));
        event_.setDateTo(new Date(1416143192915l));
        event_.setDescription("first event");
        event_.setTitle("event");
        event_.setAttenders(list);

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
        event_.set_id(10L);
        event_.setDateFrom(new Date());
        event_.setDateTo(new Date());
        event_.setDescription("first event");
        event_.setTitle("event");
        calendarService.deleteEvent(event_);
    }

    @RequestMapping(value = "/find_events_for_person/{id}/{start}/{end}", produces="application/json" )
    @ResponseBody
    public List<Event> findEventsForPerson(@PathVariable Long id, @PathVariable Long start, @PathVariable Long end) {

        return calendarService.findEventsForPerson(id, new Date(start), new Date(end));

    }

    @RequestMapping(value = "/delete_person")
    public void deletePerson(@RequestBody String json) {
        Person sasha = new Person();
        sasha.setId(5L);
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
