package com.diosoft.training.project.web;


import com.diosoft.training.project.service.CalendarService;
import com.diosoft.training.project.persistence.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    private List<Event> listEvents() {

        return calendarService.listEvent();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public List<Event> addEvent(@RequestBody String json) {

        Event event = null;
        try {
            event = new ObjectMapper().readValue(json, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.add(event);
        return calendarService.listEvent();

    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public List<Event> deleteContactWs(@RequestBody String json) {


        Event event = null;
        try {
            event = new ObjectMapper().readValue(json, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarService.remove(event);
        return calendarService.listEvent();
    }


    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }
}
