package com.epam.airline.exceptions;

import com.epam.airline.planes.Plane;

/**
 * Created by ivan on 6/25/14.
 */
public class PlaneIllegalParameters extends RuntimeException {

    public PlaneIllegalParameters(String message) {
        super(message);
    }
}
