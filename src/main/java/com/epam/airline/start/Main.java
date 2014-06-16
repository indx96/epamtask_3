package com.epam.airline.start;

import com.epam.airline.air.Airliner;
import com.epam.airline.air.Fighter;
import com.epam.airline.air.Hangar;
import com.epam.airline.air.Plane;
import com.epam.airline.parsing.PlaneParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class Main {
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String... args) {
        Hangar hangar = new Hangar();
        hangar.addPlanes(PlaneParser.getInstance().parse(new File("planes.xml")));
        log.debug("Planes with tankeSize in range " + 10 + "-" + 32);
        hangar.getPlanesWithTankeRange(10, 32).forEachRemaining(log::debug);
        log.debug("Sorted planes");
        hangar.getSortedPlanes(new Comparator<Plane>() {
            @Override
            public int compare(Plane o1, Plane o2) {
                return Float.compare(o1.getFlyRange(), o2.getFlyRange());
            }
        }).forEach(log::debug);
    }
}
