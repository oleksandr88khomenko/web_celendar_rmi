package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Event;
import com.diosoft.training.project.persistence.model.xml.EventAdapter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by oleksandr_khomenko on 14.11.2014.
 */

@Component
public class XMLProcessor implements Runnable {

    private String action;

    private Event event;

    static int count = 0;

    public XMLProcessor() {

    }

    public XMLProcessor(String action, Event event) {

        this.action = action;
        this.event = event;
    }

    public void add(Event event) {
        //local code review (vtegza): clean up code @ 12/6/2014
        System.out.println("Xmlprocessor:add event called:" + ++count);
        EventAdapter eventAdapter = new EventAdapter(event);
        try {
            JAXBContext context = JAXBContext.newInstance(EventAdapter.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(event, System.out);

            File file = new File("Event" + event.get_id() + ".xml");
            marshaller.marshal(event, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void remove(Event event) {

        File file = new File("Event" + event.get_id() + ".xml");
        file.delete();

    }

    @Override
    public void run() {

        if (action == "add") {
            add(event);

        }
        if (action == "remove") {
            remove(event);
        }

    }

}
