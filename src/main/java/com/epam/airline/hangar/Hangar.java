package com.epam.airline.hangar;

import com.epam.airline.planes.Plane;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ivan on 6/16/14.
 */
public class Hangar {

    private ArrayList<Plane> planes = new ArrayList<Plane>();

    public void addPlanes(Iterable<Plane> planes) {
        planes.forEach(this.planes::add);
    }

    public void addPlanes(Plane... planes) {
        for(Plane plane : planes) {
            this.planes.add(plane);
        }
    }

    public Iterable<Plane> getPlanes() {
        return Collections.unmodifiableList(planes);
    }
}
