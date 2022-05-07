package raytracing.rendering;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;

/**
 * Represents a light source.
 * @author Mathieu Niord
 */
public class Light {

    private final Vec3d position;
    private final Color spec, diff;
    private final double intensity;

    public Light(final Vec3d position, final Color specular, final Color diffuse, final double intensity) {
        this.position = position;
        this.spec = specular;
        this.diff = diffuse;
        this.intensity = intensity;
    }

    public Color getSpecularLight() { return spec; }

    public Color getDiffuseLight() { return diff; }

    public double getIntensity() { return intensity; }

    public Vec3d getDirection(Vec3d point) {
        return position.sub(point);
    }
}
