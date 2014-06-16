package com.epam.airline.air;

import java.util.Map;

/**
 * Created by ivan on 6/16/14.
 */
public class Airliner extends Plane {

    private int crewAmount;
    private int passengersAmount;

    public int getCrewAmount() {
        return crewAmount;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    private Airliner(Builder builder) {
        super(builder);
        this.crewAmount = builder.crewAmount;
        this.passengersAmount = builder.passengersAmount;
    }

    public static class Builder extends Plane.Builder {
        private int crewAmount;
        private int passengersAmount;

        public Builder(String name,
                       float length,
                       float height,
                       float wingspan,
                       float flyRange,
                       float tankeSize,
                       int crewAmount,
                       int passengerAmount) {
            super(name, length, height, wingspan, flyRange, tankeSize);

            this.crewAmount = crewAmount;
            this.passengersAmount = passengerAmount;
        }

        @Override
        public Plane build() {
            return new Airliner(this);
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
        if (!super.equals(o)) {
            return false;
        }

        Airliner airliner = (Airliner) o;
        if (crewAmount != airliner.crewAmount) {
            return false;
        }
        if (passengersAmount != airliner.passengersAmount) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + crewAmount;
        result = 31 * result + passengersAmount;
        return result;
    }

    @Override
    public String toString() {
        return "Airliner{" +
                "crewAmount=" + crewAmount +
                ", passengersAmount=" + passengersAmount +
                " " + super.toString();
    }
}
