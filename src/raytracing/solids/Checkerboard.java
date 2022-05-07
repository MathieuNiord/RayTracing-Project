package raytracing.solids;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;

/**
 * Inherited class from {@link Plane} which permits the generation of a checkerboard pattern.
 * @author Mathieu Niord
 */
public class Checkerboard extends Plane {

    private static final double SIZE = 150.0D;
    private final Color primary, secondary;

    public Checkerboard(
            double distance,
            Vec3d normal,
            Color specular,
            double shininess, double reflectivity,
            double transmission, double refractionIndex
    ) {
        super(distance, normal, Color.WHITE, specular, shininess, reflectivity, transmission, refractionIndex);
        primary = Color.WHITE;
        secondary = Color.BLACK;
    }

    public Checkerboard(
            double distance,
            Vec3d normal,
            Color primary, Color secondary, Color specular,
            double shininess, double reflectivity,
            double transmission, double refractionIndex
    ) {
        super(distance, normal, primary, specular, shininess, reflectivity, transmission, refractionIndex);
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public Color getColor(final Vec3d point) {
        final double x = point.x - Math.floor(point.x / SIZE) * SIZE;
        final double z = point.z - Math.floor(point.z / SIZE) * SIZE;
        if ((x < SIZE / 2 && z >= SIZE / 2) || (x >= SIZE / 2 && z < SIZE / 2)) return secondary;
        return primary;
    }
}
