package com.epam.airline.parsing.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class PlaneValidator {
    private static  PlaneValidator instance;

    public static PlaneValidator getInstance(){
        if (instance == null) {
            instance = new PlaneValidator();
        }
        return instance;
    }

    public void validate(String pathToXML, String pathToXSD)
            throws SAXException, IOException {
        Source xmlFile = new StreamSource(new File(pathToXML));
        File schemaFile = new File(pathToXSD);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }

}
