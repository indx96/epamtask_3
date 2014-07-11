package com.epam.airline.parsing;

import com.epam.airline.parsing.PlaneParser;
import com.epam.airline.parsing.validation.PlaneValidator;
import com.epam.airline.planes.Airliner;
import com.epam.airline.planes.Fighter;
import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

class StaXParser implements PlaneParser {

    StaXParser(){}

    @Override
    public Iterable<Plane> parse(String pathToXML, String pathToXSD)
            throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        PlaneValidator.getInstance().validate(pathToXML, pathToXSD);

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader
                (pathToXML, new FileInputStream(pathToXML));

        LinkedList<Plane> planes = new LinkedList<>();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch ( ((StartElement)event).getName().getLocalPart()) {

                    case "plane" : {
                        planes.add(parsePlane(reader));
                        break;
                    }


                    case "fighter": {
                        planes.add(parseFighter(reader));
                        break;
                    }

                    case "airliner": {
                        planes.add(parseAirliner(reader));
                        break;
                    }
                }
            }
        }
        return planes;
    }

    private Plane parsePlane(XMLEventReader reader) throws XMLStreamException {
        String name = null;
        float length = 0;
        float height = 0;
        float wingspan = 0;
        float flyRange = 0;
        float tankeSize = 0;

        do {
            XMLEvent event = reader.nextEvent();
            if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                if ( "tanke-size".equals(((EndElement) event).getName().getLocalPart()) ) {
                    break;
                }
            }

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch ( ((StartElement) event).getName().getLocalPart() ) {
                    case "name": {
                        name = reader.nextEvent().asCharacters().getData();
                        break;
                    }

                    case "length": {
                        length = Float.parseFloat(reader.nextEvent().asCharacters().getData());
                        break;
                    }

                    case "height": {
                        height = Float.parseFloat(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                    case "wingspan": {
                        wingspan = Float.parseFloat(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                    case "fly-range": {
                        flyRange = Float.parseFloat(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                    case "tanke-size": {
                        tankeSize = Float.parseFloat(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                }

            }
        } while(true);
        return new Plane.Builder(name, length, height, wingspan, flyRange, tankeSize).build();
    }

    private Fighter parseFighter(XMLEventReader reader) throws XMLStreamException {
        Plane basePlane = parsePlane(reader);
        HashMap <Fighter.WeaponType, Integer> map = new HashMap<>();

        do {
            XMLEvent event = reader.nextEvent();
            if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                if ( "weapons".equals(((EndElement) event).getName().getLocalPart()) ) {
                    break;
                }
            }

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch ( ((StartElement) event).getName().getLocalPart() ) {
                    case "bombs":{
                        int bombsCount = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                        map.put(Fighter.WeaponType.BOMBS, bombsCount);
                        break;
                    }
                    case "guns":{
                        int gunsCount = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                        map.put(Fighter.WeaponType.GUNS, gunsCount);
                        break;
                    }
                }

            }
        } while(true);
        return new Fighter.Builder(basePlane, map).build();
    }

    private  Airliner parseAirliner(XMLEventReader reader) throws XMLStreamException {
        Plane basePlane = parsePlane(reader);
        int crewAmount = 0;
        int passengersAmount = 0;

        do {
            XMLEvent event = reader.nextEvent();
            if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                if ( "passengers-amount".equals(((EndElement) event).getName().getLocalPart()) ) {
                    break;
                }
            }

            if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                switch ( ((StartElement) event).getName().getLocalPart() ) {
                    case "crew-amount":{
                        crewAmount = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                    case "passengers-amount": {
                        passengersAmount = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                        break;
                    }
                }


            }
        } while(true);
        return new Airliner.Builder(basePlane, crewAmount, passengersAmount).build();
    }
}
