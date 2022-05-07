package raytracing.maths;

/**
 * A 3D vector class with double precision.
 * @author Mathieu Niord
 */
public class Vec3d {

    /**
     * The x, y and z coordinates.
     */
    public double x, y, z;

    /**
     * Constructs a new vector with all components set to 0.
     */
    public Vec3d() {
        this.x = this.y = this.z = 0.0D;
    }

    /**
     * Constructs a new vector with the given coordinates.
     * @param x the x coordinate {@code double}.
     * @param y the y coordinate {@code double}.
     * @param z the z coordinate {@code double}.
     */
    public Vec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor.
     * @param v The vector to copy {@link Vec3d}.
     */
    public Vec3d(Vec3d v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Return the dot product of the current vector with v in a new vector
     * @param v The vector to dot with {@link Vec3d}.
     * @return the dot product of the current vector with v {@code double}.
     */
    public double dot(Vec3d v) {
        return (this.x * v.x + this.y * v.y + this.z * v.z);
    }

    /**
     * Return the scale of the current vector with a {@code double} scale value s in a new vector
     * @param s The scale {@code double}.
     * @return a new vector according the scaling of the current vector.
     */
    public Vec3d scale(double s) {
        return new Vec3d(this.x * s, this.y * s, this.z * s);
    }

    /**
     * Set the scale of the current vector with a {@code double} scale value s in the current vector
     * @param s The scale {@code double}.
     */
    public void setScale(double s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    /**
     * Return the sum of two vectors in a new vector.
     * @param v The vector to add {@link Vec3d}.
     * @return The sum of the two vectors.
     */
    public Vec3d add(Vec3d v) {
        return new Vec3d(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * Return the difference of two vectors in a new vector.
     * @param v The vector to subtract {@link Vec3d}.
     * @return The difference of the two vectors.
     */
    public Vec3d sub(Vec3d v) {
        return new Vec3d(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    /**
     * Compute the length of the vector.
     * @return The length of the vector {@code double}.
     */
    public double length() {
        return (Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    /**
     * Compute the square length of the vector.
     * @return The square length of the vector {@code double}.
     */
    public double lengthSquare()
    {
        return (this.x*this.x+this.y*this.y+this.z*this.z);
    }

    /**
     * Normalize the vector.
     * @return A new normalized vector {@link Vec3d}.
     */
    public Vec3d normalize() {
        return (this.lengthSquare() == 0.0D) ? this : this.scale(1.0D / this.length());
    }

    /**
     * Normalize the current vector.
     */
    public void setNormalize() {
        this.setScale(1.0D / this.length());
    }
}
