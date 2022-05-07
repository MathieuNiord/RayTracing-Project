package raytracing.solids;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;

/**
 * Inherited class from {@link Solid} which permits the generation of a Sphere according its center point.
 * @author Mathieu Niord
 */
public class Sphere extends Solid {

    private final Vec3d center;
    private final double radius;

    public Sphere(
            double radius, Vec3d center,
            Color color, Color specular,
            double shininess, double reflectivity,
            double transmission, double refractIndex
    ) {
        super(color, specular, shininess, reflectivity, transmission, refractIndex);
        this.center = new Vec3d(center);
        this.radius = radius;
    }

    /**
     * Get intersection discriminant between ray and the sphere
     * @param P Start point of our ray source
     * @param v The ray direction
     * @return The intersection between the ray and the object
     */
    @Override
    public double getIntersection(Vec3d P, Vec3d v) {

        Vec3d L = P.sub(center);

        double
                a = (v.dot(v)),
                b = (v.scale(2.0D).dot(L)),
                c = (L.dot(L) - (radius * radius));

        double delta = (b * b) - (4.0D * a * c);

        // Only one intersection
        if (delta == 0.0D) {
            double t = (-b / (2.0D * a));
            if (t > 0.0D) return t;
        }

        // Two intersections
        else if (delta > 0.0D) {
            double t1 = (-b - Math.sqrt(delta)) / (2.0D * a);
            double t2 = (-b + Math.sqrt(delta)) / (2.0D * a);
            if (t1 < 0.0001D && t2 > 0.0001D) return t2;
            else if (t1 > 0.0001D && t1 < t2) return t1;
        }

        return -1.0D;
    }

    @Override
    public Vec3d getNormal(Vec3d P) {
        return P.sub(center).normalize();
    }

}
