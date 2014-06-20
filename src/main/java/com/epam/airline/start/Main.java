package com.epam.airline.start;

import com.epam.airline.hangar.Hangar;
import com.epam.airline.hangar.hangarprocessors.HangarPlanesFilter;
import com.epam.airline.hangar.hangarprocessors.HangarPlanesSorter;
import com.epam.airline.parsing.PlaneParser;
import com.epam.airline.planes.Plane;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Comparator;

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
        HangarPlanesFilter.getInstance()
                .filterByTankeSizeRange(10, 32, hangar)
                .forEach(log::debug);

        log.debug("planes, sorted by fly range");
        Comparator<Plane> flyRangeCompator =
                (plane1, plane2) -> Float.compare(plane1.getFlyRange(), plane2.getFlyRange());

        HangarPlanesSorter.getInstance()
                .planesSortedBy(flyRangeCompator, hangar)
                .forEach(log::debug);
    }
}
