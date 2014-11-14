package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 29.10.2014.
 */
@Repository
public class EventDAOImp implements EventDAO {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Event> findAll() {
        return mongoTemplate.findAll(Event.class);
    }

    @Override
    public void add(Event event) {
//        Event event_ = new Event();
//        event_.set_id(1L);
//        event_.setDateFrom(new Date());
//        event_.setDateTo(new Date());
//        event_.setDescription("first event");
//        event_.setTitle("event");
        mongoTemplate.save(event);
    }

    @Override
    public void delete(Long id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), Event.class);
    }

    @Override
    public List<Event> findEventForPerson(Long id, Date start, Date end) {
        return mongoTemplate.find(new Query(Criteria.where("attenders.$id").is(id).and("start").gt(start).and("end").lt(end)), Event.class);

    }
}
