package com.jpixel.math;

/**
 * Helper class for basic maths-related functions.
 *
 * @author Denikson
 * @version 1.0
 * @since 24.12.2014
 */
public class MathUtils {

    /**
     * Rotates the given point around origin.
     *
     * @param angle Angle of rotation in radians.
     * @param x X coordinate of the point to rotate.
     * @param y Y coordinate of the point to rotate.
     * @return X coordinate of the rotated point.
     */
    public static double rotate_x(double angle, double x, double y){
        double s = Math.sin(angle);
        double c = Math.cos(angle);

        return x * c - y * s;
    }

    /**
     * Rotates the given point around origin.
     *
     * @param angle Angle of rotation in radians.
     * @param x X coordinate of the point to rotate.
     * @param y Y coordinate of the point to rotate.
     * @return Y coordinate of the rotated point.
     */
    public static double rotate_y(double angle, double x, double y){
        double s = Math.sin(angle);
        double c = Math.cos(angle);

        return x * s + y * c;
    }
}
