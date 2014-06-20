package com.epam.airline.hangar.hangarprocessors;

import com.epam.airline.hangar.Hangar;
import com.epam.airline.planes.Plane;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by ivan on 6/20/14.
 */
public class HangarPlanesSorter {
    private static HangarPlanesSorter instance;

    private HangarPlanesSorter() {
    }

    public static HangarPlanesSorter getInstance() {
        if (instance == null) {
            instance = new HangarPlanesSorter();
        }
        return instance;
    }

    public Iterable<Plane> planesSortedBy(Comparator<Plane> comparator, Hangar hangar) {
        SortedSet<Plane> sortedPlanes = new TreeSet<>(comparator);
        hangar.getPlanes().forEach(sortedPlanes::add);
        return sortedPlanes;
    }
}
