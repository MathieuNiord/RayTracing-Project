package raytracing.solids;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;

/**
 * A solid is a 3D object that can be intersected by a ray.
 * @author Mathieu Niord
 */
public abstract class Solid {

    protected Color color, spec;
    protected double shininess, reflexivity, transmission, refractIndex;

    Solid(
            Color color, Color specular,
            double shininess, double reflexivity,
            double transmission, double refractIndex
    ) {
        this.color = color;
        this.spec = specular;
        this.shininess = shininess;
        this.reflexivity = reflexivity;
        this.transmission = transmission;
        this.refractIndex = refractIndex;
    }

    public Color getColor(Vec3d p) { return color; }

    public Color getSpecular() { return spec; }

    public double getShininess() { return shininess; }

    public double getReflection() { return reflexivity; }

    public double getTransmission() { return transmission; }

    public double getRefractionIndex() { return refractIndex; }

    /**
     * Returns the intersection point between the ray and the solid.
     * @param P The ray origin {@link Vec3d}.
     * @param v The ray direction {@link Vec3d}.
     * @return The root value of intersection, or -1.0D if there is no intersection {@code Double}.
     */
    public double getIntersection(Vec3d P, Vec3d v) { return 0; }

    /**
     * Returns the normal vector at the given point.
     * @param P The point {@link Vec3d}.
     * @return The normal vector {@link Vec3d}.
     */
    public Vec3d getNormal(Vec3d P) { return new Vec3d(); }

}
