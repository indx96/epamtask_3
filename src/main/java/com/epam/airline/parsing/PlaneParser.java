package com.epam.airline.parsing;

import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Created by ivan on 7/7/14.
 */
public interface PlaneParser {

    public Iterable<Plane> parse(String pathToXML, String pathToXSD) throws IOException, SAXException, ParserConfigurationException, XMLStreamException;
}
