package com.epam.airline.hangar.hangarprocessors;

import com.epam.airline.hangar.Hangar;
import com.epam.airline.planes.Plane;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivan on 6/20/14.
 */
public class HangarPlanesFilter {
    private static HangarPlanesFilter instance;

    private HangarPlanesFilter() {
    }

    public static HangarPlanesFilter getInstance() {
        if (instance == null) {
            instance = new HangarPlanesFilter();
        }
        return instance;
    }

    public Iterable<Plane> filterByTankeSizeRange(float leftBorder,
                                                  float rigthBorder,
                                                  Hangar hangar) {
        List<Plane> resList = new LinkedList<Plane>();
        for (Plane plane : hangar.getPlanes()) {
            float tankSize = plane.getTankeSize();
            if (leftBorder <=  tankSize && tankSize <= rigthBorder) {
                resList.add(plane);
            }
        }
        return resList;
    }
}
