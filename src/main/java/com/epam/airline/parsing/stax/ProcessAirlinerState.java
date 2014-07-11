package com.epam.airline.parsing.stax;

import com.epam.airline.planes.Airliner;
import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by ivan on 7/11/14.
 */
class ProcessAirlinerState extends DefaultHandler implements ParsingContext.ContextState {
    private ParsingContext context;
    private ProcessPlaneState parentState = new ProcessPlaneState(context);

    public ProcessAirlinerState(ParsingContext context) {
        this.context = context;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("passengers-amount".equals(qName)) {
            Plane basePlane = parentState.createPlaneByValues();
            int crewAmount = Integer.parseInt((String) parentState.values.get(6));
            int passengersAmount = Integer.parseInt((String) parentState.values.get(7));
            Airliner airliner = new Airliner.Builder(basePlane, crewAmount, passengersAmount).build();
            context.addPlane(airliner);
            parentState.values.clear();
            context.setState(context.PROCESS_PLANES);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        parentState.characters(ch, start, length);
    }
}
