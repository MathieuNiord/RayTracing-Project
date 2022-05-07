package raytracing.solids;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;

/**
 * Inherited class from {@link Solid} which permits the generation of a plane according its normal.
 * @author Mathieu Niord
 */
public class Plane extends Solid {

    protected final double distance;
    protected final Vec3d normal;

    public Plane(
            double distance, Vec3d normal,
            Color color, Color specular,
            double shininess, double reflectivity,
            double transmission, double refractIndex
    ) {
        super(color, specular, shininess, reflectivity, transmission, refractIndex);
        this.distance = distance;
        this.normal = normal;
    }

    @Override
    public double getIntersection(Vec3d P, Vec3d v) {
        final double t = (normal.dot(v) != 0.0D) ? (-(normal.dot(P)) - distance) / (normal.dot(v)) : -1.0D;
        return (t > 0.0001D) ? t : -1.0D;
    }

    @Override
    public Vec3d getNormal(Vec3d P) {
        return normal;
    }
}
