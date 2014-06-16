package com.epam.airline.air;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by ivan on 6/16/14.
 */
public class Plane {
    protected static Logger log = Logger.getLogger(Plane.class); // Знаю, что protected не существует, и всё такое
    private String name;                                         // но не костыли же писать
    private float length;
    private float height;
    private float wingspan;
    private float flyRange;
    private float tankeSize;
    private Optional<Float> cabinVolume;
    private Optional<Float> operationalCeiling;
    private Optional<Float> runawayLength;

    public String getName() {
        return name;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getWingspan() {
        return wingspan;
    }

    public float getFlyRange() {
        return flyRange;
    }

    public float getTankeSize() {
        return tankeSize;
    }

    public Optional<Float> getCabinVolume() {
        return cabinVolume;
    }

    public Optional<Float> getOperationalCeiling() {
        return operationalCeiling;
    }

    public Optional<Float> getRunawayLength() {
        return runawayLength;
    }

    protected Plane(Builder builder) {
        this.name = builder.name;
        this.length = builder.length;
        this.height = builder.height;
        this.wingspan = builder.wingspan;
        this.flyRange = builder.flyRange;
        this.tankeSize = builder.tankeSize;
        this.cabinVolume = builder.cabinVolume;
        this.operationalCeiling = builder.operationalCeiling;
        this.runawayLength = builder.runawayLength;
        log.debug("Plane created:" + toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Plane otherPlane = (Plane) o;

        if (Float.compare(otherPlane.flyRange, flyRange) != 0) {
            return false;
        }
        if (Float.compare(otherPlane.height, height) != 0) {
            return false;
        }
        if (Float.compare(otherPlane.length, length) != 0) {
            return false;
        }
        if (Float.compare(otherPlane.tankeSize, tankeSize) != 0) {
            return false;
        }
        if (Float.compare(otherPlane.wingspan, wingspan) != 0) {
            return false;
        }
        if (cabinVolume.isPresent() && otherPlane.cabinVolume.isPresent()) {
            if (!cabinVolume.get().equals(otherPlane.cabinVolume.get())) {
                return false;
            }
        } else {
            return false;
        }
        if (operationalCeiling.isPresent() && otherPlane.operationalCeiling.isPresent()) {
            if (!operationalCeiling.get().equals(otherPlane.operationalCeiling.get())) {
                return false;
            }
        } else {
            return false;
        }
        if (runawayLength.isPresent() && otherPlane.runawayLength.isPresent()) {
            if (!runawayLength.get().equals(otherPlane.runawayLength.get())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (length != +0.0f ? Float.floatToIntBits(length) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (wingspan != +0.0f ? Float.floatToIntBits(wingspan) : 0);
        result = 31 * result + (flyRange != +0.0f ? Float.floatToIntBits(flyRange) : 0);
        result = 31 * result + (tankeSize != +0.0f ? Float.floatToIntBits(tankeSize) : 0);
        result = 31 * result + (Float.floatToIntBits(cabinVolume.orElse(0f)));
        result = 31 * result + (Float.floatToIntBits(operationalCeiling.orElse(0f)));
        result = 31 * result + (Float.floatToIntBits(runawayLength.orElse(0f)));
        return result;
    }

    @Override
    public String toString() {
        String resString = "Plane{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", height=" + height +
                ", wingspan=" + wingspan +
                ", flyRange=" + flyRange +
                ", tankeSize=" + tankeSize;
        StringBuilder builder = new StringBuilder();
        builder.append(resString);
        if (cabinVolume.isPresent()) {
            builder.append(", cabin volume=" + cabinVolume.get());
        }
        if (operationalCeiling.isPresent()) {
            builder.append(", operational ceiling=" + operationalCeiling.get());
        }
        if (runawayLength.isPresent()) {
            builder.append(", runaway length" + runawayLength.get());
        }
        builder.append('}');
        return builder.toString();
    }

    public static class Builder {
        private String name;
        private float length;
        private float height;
        private float wingspan;
        private float flyRange;
        private float tankeSize;
        private Optional<Float> cabinVolume = Optional.empty();
        private Optional<Float> operationalCeiling = Optional.empty();
        private Optional<Float> runawayLength = Optional.empty();

        public Builder(String name, float length, float height, float wingspan, float flyRange, float tankeSize) {
            if (name == null) {
                throw new IllegalArgumentException();
            }

            this.name = name;
            this.length = length;
            this.height = height;
            this.wingspan = wingspan;
            this.flyRange = flyRange;
            this.tankeSize = tankeSize;
        }

        public Builder setCabinVolume(float cabinVolume) {
            this.cabinVolume = Optional.of(cabinVolume);
            return this;
        }

        public Builder setOperationalCeiling(float operationalCeiling) {
            this.operationalCeiling = Optional.of(operationalCeiling);
            return this;
        }

        public Builder setRunawayLength(float runawayLength) {
            this.runawayLength = Optional.of(runawayLength);
            return this;
        }

        public Plane build() {
            return new Plane(this);
        }
    }
}
