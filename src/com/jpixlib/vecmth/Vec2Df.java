package com.jpixlib.vecmth;

/**
 * Point vector (Double).
 * 
 * @author Denis Zhidkikh
 * @version 1.0
 * @since 18.5.2013
 */
public class Vec2Df {
	/**
	 * Zero vector.
	 */
	public static final Vec2Df Zero = new Vec2Df(0.0F, 0.0F);

	/**
	 * Vector coordinates.
	 */
	public float x, y;

	public Vec2Df(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Initialize vector.
	 * 
	 * @param x
	 *            Coordinate in X -axis.
	 * @param y
	 *            Coordinate in Y -axis.
	 */
	public Vec2Df(Vec2Df vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	/**
	 * Create copy of the vector.
	 * 
	 * @param vec
	 *            Vector to copy.
	 */
	public Vec2Df(Vec2Di vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	/**
	 * Create copy of the vector.
	 * 
	 * @param vec
	 *            Vector to copy.
	 */
	public Vec2Df(Vec2Dd vec) {
		this.x = (float) vec.x;
		this.y = (float) vec.y;
	}

	/**
	 * Convert polar coordinate vector (magnitude and direction) to point vector
	 * (x and y).
	 * 
	 * @param vec
	 *            Vector to convert.
	 */
	public Vec2Df(Vec2DPolar vec) {
		this.x = (float) (vec.length * Math.sin(vec.angle));
		this.y = (float) (vec.length * Math.cos(vec.angle));
	}

	/**
	 * Compute the dot product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The dot product of this and second vector.
	 */
	public final double dot(Vec2Di vec) {
		return this.x * vec.x + this.y * vec.y;
	}

	/**
	 * Compute the dot product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The dot product of this and second vector.
	 */
	public final double dot(Vec2Dd vec) {
		return this.x * vec.x + this.y * vec.y;
	}

	/**
	 * Compute the dot product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The dot product of this and second vector.
	 */
	public final double dot(Vec2Df vec) {
		return this.x * vec.x + this.y * vec.y;
	}

	/**
	 * Compute the cross product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The cross product of this and second vector.
	 */
	public final double cross(Vec2Di vec) {
		return vec.x * this.y - vec.y * this.x;
	}

	/**
	 * Compute the cross product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The cross product of this and second vector.
	 */
	public final double cross(Vec2Dd vec) {
		return vec.x * this.y - vec.y * this.x;
	}

	/**
	 * Compute the cross product of the vectors.
	 * 
	 * @param vec
	 *            Second vector.
	 * @return The cross product of this and second vector.
	 */
	public final double cross(Vec2Df vec) {
		return vec.x * this.y - vec.y * this.x;
	}

	/**
	 * Gets the length of the vector (distance from origin).
	 * 
	 * @return The length of the vector.
	 */
	public final double length() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Gets the distance from the point.
	 * 
	 * @param x
	 *            Point's X coordinate.
	 * @param y
	 *            Point's Y coordinate.
	 * @return Distance between the vector and the point.
	 */
	public final double distance(float x, float y) {
		float dx = (this.x - x);
		float dy = (this.y - y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Gets the distance between this vector and another.
	 * 
	 * @param vec
	 *            Vector.
	 * @return Distance between this and given vector.
	 */
	public final double distance(Vec2Df vec) {
		float dx = (this.x - vec.x);
		float dy = (this.y - vec.y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Gets the distance between this vector and another.
	 * 
	 * @param vec
	 *            Vector.
	 * @return Distance between this and given vector.
	 */
	public final double distance(Vec2Dd vec) {
		double dx = (this.x - vec.x);
		double dy = (this.y - vec.y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Gets the distance between this vector and another.
	 * 
	 * @param vec
	 *            Vector.
	 * @return Distance between this and given vector.
	 */
	public final double distance(Vec2Di vec) {
		float dx = (this.x - vec.x);
		float dy = (this.y - vec.y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Normalizes the vector (setting vector's length to 1.0 while keeping the
	 * direction).
	 * 
	 * @return This vector. Used to perform multiple actions.
	 */
	public final Vec2Df normalize() {
		double factor = 1.0 / length();
		x *= factor;
		y *= factor;
		return this;
	}

	/**
	 * Scales the vector's length (while keeping the direction).
	 * 
	 * @param scale
	 *            Scale amount.
	 * @return This vector. Used to perform multiple actions.
	 */
	public final Vec2Df scale(double scale) {
		double factor = scale / length();
		x *= factor;
		y *= factor;
		return this;
	}

	/**
	 * Gets the angle of the vector (in radians) as if it was a line.<br>
	 * 
	 * <b>Note:</b> The result is in range [0, PI / 2]. For proper angle use
	 * {@link #getDirectionAngle()}.
	 * 
	 * @return Angle of the vector.
	 */
	public final double getMultiplierAngle() {
		return Math.atan(y / x);
	}

	/**
	 * Gets the angle of the vector. (In radians).<br>
	 * 
	 * <b>Note:</b> The result's range is [0, PI * 2]. The angle increases
	 * counter-clockwise.
	 * 
	 * @return Angle of the vector.
	 */
	public final double getDirectionAngle() {
		return Math.atan2(y, x) + Math.PI / 2;
	}

	@Override
	public String toString() {
		return String.format("Vec2D<Float>: [%1$.5d, %2&.5d] : %3$", x, y, hashCode());
	}

}
