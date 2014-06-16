package com.epam.airline.air;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 6/16/14.
 */
public class Fighter extends Plane {

    public enum WeaponType{Bombs, Guns};
    private Map<WeaponType, Integer> gunsOnBoard;

    public Map<WeaponType, Integer> getGunsOnBoard() {
        return Collections.unmodifiableMap(gunsOnBoard);
    }

    private Fighter(Builder builder) {
        super(builder);
        this.gunsOnBoard = builder.gunsOnBoard;
    }

    public static class Builder extends Plane.Builder {
        private Map<WeaponType, Integer> gunsOnBoard = new HashMap<>();

        public Builder(String name,
                       float length,
                       float height,
                       float wingspan,
                       float flyRange,
                       float tankeSize,
                       Map<WeaponType, Integer> gunsOnBoard) {
            super(name, length, height, wingspan, flyRange, tankeSize);
            if (gunsOnBoard == null) {
                throw new IllegalArgumentException();
            }
            this.gunsOnBoard = gunsOnBoard;
        }

        @Override
        public Fighter build() {
            return new Fighter(this);
        }
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
        if (!gunsOnBoard.equals(fighter.gunsOnBoard)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (gunsOnBoard.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return  "Fighter{" +
                "gunsOnBoard=" + gunsOnBoard +
                 " " + super.toString();
    }
}
