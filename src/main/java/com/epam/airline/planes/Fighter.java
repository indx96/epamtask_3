package com.epam.airline.planes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 6/16/14.
 */
public class Fighter extends Plane {

    public enum WeaponType{Bombs, Guns};
    private Map<WeaponType, Integer> weaponsOnBoard;

    public Map<WeaponType, Integer> getWeaponsOnBoard() {
        return Collections.unmodifiableMap(weaponsOnBoard);
    }

    private Fighter(Builder builder) {
        super(builder);
        this.weaponsOnBoard = builder.weaponsOnBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (! super.equals(o) ) {
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
        return  "Fighter{" +
                "gunsOnBoard=" + weaponsOnBoard +
                " " + super.toString() + "}";
    }

    public static class Builder extends Plane.Builder {
        private Map<WeaponType, Integer> weaponsOnBoard = new HashMap<>();

        public Builder(String name,
                       float length,
                       float height,
                       float wingspan,
                       float flyRange,
                       float tankeSize,
                       Map<WeaponType, Integer> weaponsOnBoard) {
            super(name, length, height, wingspan, flyRange, tankeSize);
            if (weaponsOnBoard == null) {
                throw new IllegalArgumentException();
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
