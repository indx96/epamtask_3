package com.epam.airline.parsing;

import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ivan on 7/7/14.
 */
public class ParserFactory {
    private static Logger log = Logger.getLogger(ParserFactory.class);

    public static PlaneParser getParser(Type type) {
        switch (type) {

            case DOM:
                return new DOMParser();

            case STAX:
                return new StaXParser();

            default:
                log.error("wrong enum type: " + type);
                throw new NotImplementedException();
        }
    }

    ;

    public enum Type {DOM, STAX}

}
