package com.epam.airline.planes;

import com.epam.airline.exceptions.PlaneInvalidException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Fighter extends Plane {

    private Map<WeaponType, Integer> weaponsOnBoard;


    private Fighter(Builder builder) {
        super(builder);
        this.weaponsOnBoard = builder.weaponsOnBoard;
    }

    public Map<WeaponType, Integer> getWeaponsOnBoard() {
        return Collections.unmodifiableMap(weaponsOnBoard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Fighter fighter = (Fighter) o;
        if (!weaponsOnBoard.equals(fighter.weaponsOnBoard)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (weaponsOnBoard.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "gunsOnBoard=" + weaponsOnBoard +
                " " + super.toString() + "}";
    }

    public enum WeaponType {BOMBS, GUNS}

    public static class Builder extends Plane.Builder {
        private Map<WeaponType, Integer> weaponsOnBoard = new HashMap<>();

        public Builder(String name,
                       float length,
                       float height,
                       float wingspan,
                       float flyRange,
                       float tankeSize,
                       Map<WeaponType, Integer> weaponsOnBoard){
            super(name, length, height, wingspan, flyRange, tankeSize);
            if (weaponsOnBoard == null) {
                throw new PlaneInvalidException("weapons can't be null");
            }
            this.weaponsOnBoard = weaponsOnBoard;
        }

        public Builder(Plane plane,
                       Map<WeaponType, Integer> weaponsOnBoard){
            super(plane.getName(),
                    plane.getLength(),
                    plane.getHeight(),
                    plane.getWingspan(),
                    plane.getFlyRange(),
                    plane.getTankeSize());
            if (weaponsOnBoard == null) {
                throw new PlaneInvalidException("weapons can't be null");
            }
            this.weaponsOnBoard = weaponsOnBoard;
        }

        @Override
        public Fighter build() {
            Fighter fighter = new Fighter(this);
            log.debug("Fighter built: " + fighter);
            return fighter;
        }
    }
}
