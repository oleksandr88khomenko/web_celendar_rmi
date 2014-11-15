package com.diosoft.training.project.persistence.repository;

import com.diosoft.training.project.persistence.model.Event;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by oleksandr_khomenko on 14.11.2014.
 */

@Component
public class XMLProcessor implements Runnable {

    private String action;

    private Event event;

    public XMLProcessor() {

    }

    public XMLProcessor(String action, Event event) {

        this.action = action;
        this.event = event;
    }

    public void add(Event event) {

        try {
            JAXBContext context = JAXBContext.newInstance(Event.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(event, System.out);

            Writer writer = null;
            try {
                writer = new FileWriter("Event" + event.get_id() + ".xml");
                marshaller.marshal(event, writer);
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
