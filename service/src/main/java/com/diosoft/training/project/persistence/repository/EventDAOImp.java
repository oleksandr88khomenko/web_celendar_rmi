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
//local code review (vtegza): tests @ 11/16/2014
public class EventDAOImp implements EventDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public EventDAOImp() {

    }

    public EventDAOImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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
        //local code review (vtegza): extract methods to keep code more readable @ 11/16/2014
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Event.class);
    }

    @Override
    public List<Event> findEventForPerson(Long id, Date start, Date end) {
        //local code review (vtegza): there are an conventions for line lenght - the classic is 90, and more regular is 180 @ 11/16/2014
        return mongoTemplate.find(new Query(Criteria.where("attenders.$id").is(id).and("start").gt(start).and("end").lt(end)), Event.class);

    }
}
