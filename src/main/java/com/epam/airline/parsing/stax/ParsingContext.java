package com.epam.airline.parsing.stax;

import com.epam.airline.planes.Plane;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by ivan on 7/11/14.
 */
class ParsingContext extends DefaultHandler {

    public interface ContextState{}
    final ContextState PROCESS_PLANES = new ProcessPlanesState(this);
    final ContextState PROCESS_PLANE = new ProcessPlaneState(this);
    final ContextState PROCESS_FIGHTER = new ProcessFighterState(this);
    final ContextState PROCESS_AIRLINER = new ProcessAirlinerState(this);
    private ContextState state = PROCESS_PLANES;
    private static Logger log = Logger.getLogger(ParsingContext.class);

    private LinkedList<Plane> planes = new LinkedList<>();

    void addPlane(Plane plane) {
        planes.add(plane);
    }

    Iterable<Plane> getPlanes(){
        return Collections.unmodifiableList(planes);
    }

    void setState(ContextState state) {
        this.state = state;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.debug("start element, name: " + qName);
        ((DefaultHandler)state).startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.debug("end element, name: " + qName);
        ((DefaultHandler)state).endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.debug("characters, value: [" + new String(ch, start, length) + "]");
        ((DefaultHandler)state).characters(ch, start, length);
    }

}
