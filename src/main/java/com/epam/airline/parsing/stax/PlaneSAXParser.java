package com.epam.airline.parsing.stax;

import com.epam.airline.parsing.PlaneParser;
import com.epam.airline.parsing.validation.PlaneValidator;
import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by ivan on 7/11/14.
 */
public class PlaneSAXParser extends DefaultHandler implements PlaneParser {

    @Override
    public Iterable<Plane> parse(String pathToXML, String pathToXSD)
            throws IOException, SAXException, ParserConfigurationException, XMLStreamException {

        File schemaFile = new File(pathToXSD);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setSchema(PlaneValidator.getInstance().createSchema(pathToXSD));
        SAXParser saxParser = factory.newSAXParser();
        ParsingContext context = new ParsingContext();
        saxParser.parse(new File(pathToXML), context);
        return context.getPlanes();
    }
}
