package com.diosoft.training.project.persistence.model;

/**
 * Created by oleksandr_khomenko on 28.10.2014.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document
public class Event implements Serializable {

    @Id
    private Long id;
    private String title;
    private String description;
    //local code review (vtegza): separate Wrapper for xml, data store nad business layer @ 11/16/2014
    @DBRef
    private List<Person> attenders;
    private Date dateFrom;
    private Date dateTo;


    public Long get_id() {
        return id;
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public List<Person> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<Person> attenders) {
        this.attenders = attenders;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attenders=" + attenders +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }


    //local code review (vtegza): why id is not part of equals? @ 11/16/2014
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!attenders.equals(event.attenders)) return false;
        if (!dateFrom.equals(event.dateFrom)) return false;
        if (!dateTo.equals(event.dateTo)) return false;
        if (!description.equals(event.description)) return false;
        if (!id.equals(event.id)) return false;
        if (!title.equals(event.title)) return false;

        return true;
    }

    //local code review (vtegza): why id is not part of hashcode? @ 11/16/2014
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + attenders.hashCode();
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + dateTo.hashCode();
        return result;
    }
}
