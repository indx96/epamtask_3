package com.epam.airline.air;

import java.util.*;

/**
 * Created by ivan on 6/16/14.
 */
public class Hangar {

    private ArrayList<Plane> planes = new ArrayList<>();

    public void addPlanes(Iterable<Plane> planes) {
        planes.forEach(this.planes::add);
    }

    public void addPlanes(Plane... planes) {
        for(Plane plane : planes) {
            this.planes.add(plane);
        }
    }

    public Iterable<Plane> getSortedPlanes(Comparator<Plane> comparator) {
        planes.sort(comparator);
        return Collections.unmodifiableList(planes);
    }

    public Iterator<Plane> getPlanesWithTankeRange(float leftBorder, float rightBorder) {
        return planes.stream().filter(
                (plane) -> leftBorder <= plane.getTankeSize() &&
                        plane.getTankeSize() <= rightBorder).iterator();
    }
}
