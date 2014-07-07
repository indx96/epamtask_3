package com.epam.airline.planes;

import com.epam.airline.exceptions.PlaneIllegalParameters;
import org.apache.log4j.Logger;

import java.util.Optional;

/**
 * Created by ivan on 6/16/14.
 */
public class Plane {
    protected static Logger log = Logger.getLogger(Plane.class);
    private String name;
    private float length;
    private float height;
    private float wingspan;
    private float flyRange;
    private float tankeSize;

    protected Plane(Builder builder) {
        this.name = builder.name;
        this.length = builder.length;
        this.height = builder.height;
        this.wingspan = builder.wingspan;
        this.flyRange = builder.flyRange;
        this.tankeSize = builder.tankeSize;
    }

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
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", height=" + height +
                ", wingspan=" + wingspan +
                ", flyRange=" + flyRange +
                ", tankeSize=" + tankeSize;

    }

    public static class Builder {
        private String name;
        private float length;
        private float height;
        private float wingspan;
        private float flyRange;
        private float tankeSize;

        public Builder(String name,
                       float length,
                       float height,
                       float wingspan,
                       float flyRange,
                       float tankeSize) {
            if (name == null) {
                throw new PlaneIllegalParameters("Name can't be null");
            }
            if (length <= 0) {
                throw new PlaneIllegalParameters("Length can't be negative or zero");
            }
            if (height <= 0) {
                throw new PlaneIllegalParameters("Height can't be negative or zero");
            }
            if (wingspan <= 0) {
                throw new PlaneIllegalParameters("Wingspan can't be negative or zero");
            }
            if (flyRange <= 0) {
                throw new PlaneIllegalParameters("Fly range can't be negative or zero");
            }
            if (tankeSize <= 0) {
                throw new PlaneIllegalParameters("Length can't be negative or zero");
            }

            this.name = name;
            this.length = length;
            this.height = height;
            this.wingspan = wingspan;
            this.flyRange = flyRange;
            this.tankeSize = tankeSize;
        }

        public Plane build() {
            Plane plane = new Plane(this);
            log.debug("Plane built: " + plane);
            return plane;
        }
    }
}
