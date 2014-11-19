package com.diosoft.training.project.persistence.model.xml;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr_khomenko on 20.11.2014.
 */
@XmlRootElement
@XmlType(name = "event")
public class EventAdapter implements Serializable {

    private Long id;
    private String title;
    private String description;
    private List<PersonAdapter> attenders;
    private Date dateFrom;
    private Date dateTo;

    public EventAdapter() {

    }

    public EventAdapter(Event event) {

        this.title = event.getTitle();
        this.id = event.get_id();
        this.description = event.getDescription();
        this.attenders = new ArrayList<PersonAdapter>();
        if (event.getAttenders() != null) {
            for (Person person : event.getAttenders()) {
                this.attenders.add(new PersonAdapter(person));
            }
        }
    }

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

    @XmlElementWrapper(name = "attenders")
    @XmlElement(name = "attender")
    public List<PersonAdapter> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<PersonAdapter> attenders) {
        this.attenders = attenders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventAdapter that = (EventAdapter) o;

        if (!attenders.equals(that.attenders)) return false;
        if (!dateFrom.equals(that.dateFrom)) return false;
        if (!dateTo.equals(that.dateTo)) return false;
        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

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

    @Override
    public String toString() {
        return "EventAdapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attenders=" + attenders +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}


