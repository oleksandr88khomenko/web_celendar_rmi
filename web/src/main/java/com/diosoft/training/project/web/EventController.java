package com.diosoft.training.project.web;


import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;
import com.diosoft.training.project.service.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */
@Controller
@RequestMapping(value = "/webcalendar")
public class EventController {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping(value = "/index", produces = "application/json")
    @ResponseBody
    private List<Event> findAllEvents() {

        return calendarService.findAllEvents();
    }

    @RequestMapping(value = "/add_event", method = RequestMethod.POST)
    public void addEvent(@RequestBody String json) {

        Event event = null;
        try {
            event = new ObjectMapper().readValue(json, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.addEvent(event);

    }

    @RequestMapping(value = "/delete_event")
    public void deleteEvent(@RequestBody String json) {

        Event event = null;
        try {
            event = new ObjectMapper().readValue(json, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.deleteEvent(event);
    }

    @RequestMapping(value = "/find_events_for_person/{id}/{start}/{end}")
    @ResponseBody
    public List<Event> findEventsForPerson(@PathVariable Long id, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {

        return calendarService.findEventsForPerson(id, start, end);

    }

    @RequestMapping(value = "/delete_person")
    public void deletePerson(@RequestBody String json) {

        Person person = null;
        try {
            person = new ObjectMapper().readValue(json, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.deletePerson(person);

    }

    @RequestMapping(value = "/add_person")
    public void addPerson(@RequestBody String json) {

        Person person = null;
        try {
            person = new ObjectMapper().readValue(json, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.addPerson(person);

    }

    @RequestMapping(value = "/find_all_attenders")
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
