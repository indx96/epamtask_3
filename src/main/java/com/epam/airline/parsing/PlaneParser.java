package com.epam.airline.parsing;

import com.epam.airline.exceptions.PlaneParsingException;
import com.epam.airline.planes.Airliner;
import com.epam.airline.planes.Fighter;
import com.epam.airline.planes.Plane;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ivan on 6/16/14.
 */
public class PlaneParser {
    private static PlaneParser instance;
    public static PlaneParser getInstance(){
        if (instance == null) {
            instance = new PlaneParser();
        }
        return instance;
    }
    private PlaneParser(){}

    public Iterable<Plane> parse(File file) {
        if (file == null) {
            throw new PlaneParsingException("file can'not be null");
        }
        List<Plane> resList = new LinkedList<Plane>();
        Map<Fighter.WeaponType, Integer> m1 = new HashMap<Fighter.WeaponType, Integer>();
        m1.put(Fighter.WeaponType.BOMBS, 1);
        Map<Fighter.WeaponType, Integer> m2 = new HashMap<Fighter.WeaponType, Integer>();
        m2.put(Fighter.WeaponType.BOMBS, 1);
        Fighter f1 = (Fighter) new Fighter.Builder("f1", 1, 3, 1, 10500, 10, m1).
                setCabinVolume(1).setOperationalCeiling(1).setRunawayLength(1).build();
        resList.add(f1);
        Fighter f2 = (Fighter) new Fighter.Builder("f2", 1, 6, 1, 300, 20, m2).
                setCabinVolume(1).setRunawayLength(1).build();
        resList.add(f2);
        Airliner a1 = (Airliner) new Airliner.Builder("a1", 1, 1, 1, 10503, 1, 1, 31).
                setCabinVolume(1).setOperationalCeiling(1).setRunawayLength(1).build();
        resList.add(a1);
        Airliner a2 = (Airliner) new Airliner.Builder("a2", 1, 1, 1, 266, 1, 1, 2).
                setCabinVolume(1).setOperationalCeiling(1).setRunawayLength(1).build();
        resList.add(a2);
        Plane p1 = new Plane.Builder("p1", 1, 1, 1, 1, 1).
                setCabinVolume(1).setOperationalCeiling(1).setRunawayLength(1).build();
        resList.add(p1);
        Plane p2 = new Plane.Builder("p1", 1, 1, 1, 1, 1).
                setCabinVolume(1).setOperationalCeiling(1).setRunawayLength(1).build();
        resList.add(p2);
        return resList;
    }
}
