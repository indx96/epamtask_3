package com.epam.airline.exceptions;

import com.epam.airline.planes.Plane;

/**
 * Created by ivan on 6/25/14.
 */
public class PlaneInvalidException extends RuntimeException {

    public PlaneInvalidException() {
    }

    public PlaneInvalidException(String message) {
        super(message);
    }

    public PlaneInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaneInvalidException(Throwable cause) {
        super(cause);
    }
}
