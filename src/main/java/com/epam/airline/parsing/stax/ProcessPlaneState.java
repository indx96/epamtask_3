package com.epam.airline.parsing.stax;

import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by ivan on 7/11/14.
 */
class ProcessPlaneState extends DefaultHandler implements ParsingContext.ContextState {
    protected ArrayList values = new ArrayList();
    private ParsingContext context;

    public ProcessPlaneState(ParsingContext context) {
        this.context = context;
    }

    protected Plane createPlaneByValues(){
        String name = (String) values.get(0);
        float length = Float.parseFloat((String) values.get(1));
        float height = Float.parseFloat((String) values.get(2));
        float wingspan = Float.parseFloat((String) values.get(3));
        float flyRange = Float.parseFloat((String) values.get(4));
        float tankeSize = Float.parseFloat((String) values.get(5));
        return new Plane.Builder(
                name,
                length,
                height,
                wingspan,
                flyRange,
                tankeSize).build();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("tanke-size".equals(qName)) {
            context.addPlane(createPlaneByValues());
            context.setState(context.PROCESS_PLANES);
            values.clear();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        values.add(new String(ch, start, length));
    }
}
