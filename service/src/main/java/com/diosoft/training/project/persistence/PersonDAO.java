package com.diosoft.training.project.persistence;

import com.diosoft.training.project.persistence.model.Person;

import java.util.List;

/**
 * Created by oleksandr_khomenko on 07.11.2014.
 */
public interface PersonDAO {

    List<Person> findAll();

    void add(Person person);

    void delete(Long id);

}