package com.epam.airline.parsing.stax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by ivan on 7/11/14.
 */
class ProcessPlanesState extends DefaultHandler implements ParsingContext.ContextState {
    private ParsingContext context;

    ProcessPlanesState(ParsingContext context) {
        this.context = context;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {

            case "plane":
                context.setState(context.PROCESS_PLANE);
                break;

            case "fighter":
                context.setState(context.PROCESS_FIGHTER);
                break;

            case "airliner":
                context.setState(context.PROCESS_AIRLINER);
                break;
        }
    }

}
