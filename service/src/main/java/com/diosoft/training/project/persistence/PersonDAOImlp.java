package com.diosoft.training.project.persistence;

import com.diosoft.training.project.persistence.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleksandr_khomenko on 07.11.2014.
 */
@Repository
public class PersonDAOImlp implements PersonDAO{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Person> findAll() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public void add(Person person) {
        mongoTemplate.save(person);
    }

    @Override
    public void delete(Long id) {
       mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), Person.class);
    }
}
