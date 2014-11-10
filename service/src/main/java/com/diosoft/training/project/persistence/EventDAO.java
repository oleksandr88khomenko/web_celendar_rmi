package com.diosoft.training.project.persistence;

import com.diosoft.training.project.persistence.model.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */
public interface EventDAO {

    List<Event> findAll();

    void add(Event event);

    void delete(Long id);

    List<Event> findEventForPerson(Long id, Date start, Date end);

}
