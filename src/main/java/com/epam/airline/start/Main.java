package com.epam.airline.start;

import com.epam.airline.parsing.ParserFactory;
import com.epam.airline.parsing.PlaneParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String... args) {
        PlaneParser parser = ParserFactory.getParser(ParserFactory.Type.DOM);
        try {
            log.debug(parser.parse("src/main/resources/xml/planes.xml",
                    "src/main/resources/xml/planes.xsd").toString());
        } catch (IOException e) {
            log.error(e.toString());
        } catch (SAXException e) {
            log.error(e.toString());
        } catch (ParserConfigurationException e) {
            log.error(e.toString());
        } catch (XMLStreamException e) {
            log.error(e.toString());
        }
    }
}
