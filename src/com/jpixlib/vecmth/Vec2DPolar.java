package com.jpixlib.vecmth;

/**
 * Polar vector (length and angle).
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 18.5.2013
 */
public class Vec2DPolar {

	/**
	 * Vector's length;
	 */
	public double length;

	/**
	 * Vector's angle.
	 */
	public double angle;

	/**
	 * Initializes the vector.
	 * 
	 * @param length Vector's length (<i>r</i>).
	 * @param angle Vector's angle (<i>theta</i>).
	 */
	public Vec2DPolar(double length, double angle) {
		this.length = length;
		this.angle = angle;
	}

	/**
	 * Convert point vector to polar vector.
	 * 
	 * @param vec Point vector.
	 */
	public Vec2DPolar(Vec2Di vec) {
		this.length = vec.length();
		this.angle = Math.atan2(vec.y, vec.x);
	}

	/**
	 * Convert point vector to polar vector.
	 * 
	 * @param vec Point vector.
	 */
	public Vec2DPolar(Vec2Dd vec) {
		this.length = vec.length();
		this.angle = Math.atan2(vec.y, vec.x);
	}

	/**
	 * Convert point vector to polar vector.
	 * 
	 * @param vec Point vector.
	 */
	public Vec2DPolar(Vec2Df vec) {
		this.length = vec.length();
		this.angle = Math.atan2(vec.y, vec.x);
	}

	@Override
	public String toString() {
		return String.format("Vec2D<Polar>: [%1$.5d, %2&.5d] : %3$", length, angle, hashCode());
	}
}
