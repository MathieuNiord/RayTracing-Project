package raytracing.rendering;

import raytracing.utils.Color;
import raytracing.maths.Vec3d;
import raytracing.solids.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The scene in where planes, objects and lights are placed (in other words "what is showed").
 * @author Niord Mathieu
 */
public class Scene {

    private final ArrayList<Solid> objects = new ArrayList<>();
    private final ArrayList<Light> lights = new ArrayList<>();
    private Color ambientLight;

    public Scene() {
        ambientLight = Color.BLACK;
    }

    /**
     * Set the ambient light color of the scene.
     * @param ambientLight The ambient light color.
     */
    public void setAmbientLight(Color ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * Add an iherited object from {@link Solid} to the scene.
     * @param object The object to add.
     */
    public void addObject(Solid object) {
        objects.add(object);
    }

    /**
     * Add a {@link Light} to the scene.
     * @param light The light to add.
     */
    public void addLight(Light light) {
        this.lights.add(light);
    }

    /**
     * Add a {@link Sphere} to the scene.
     * @param radius The radius of the sphere.
     * @param center The center of the sphere.
     * @param color The color of the sphere.
     * @param specularColor The specular color of the sphere.
     * @param shininess The shininess of the sphere.
     * @param reflexivity The reflexivity of the sphere.
     * @param transparency The transparency of the sphere.
     * @param refractionIndex The refraction index of the sphere.
     */
    public void addSphere(
            final double radius,
            final Vec3d center,
            final Color color, final Color specularColor,
            final double shininess, final double reflexivity,
            final double transparency, final double refractionIndex
    ) {
        this.objects.add(
                new Sphere(
                    radius, center, color, specularColor, shininess, reflexivity, transparency, refractionIndex
                )
        );
    }

    /**
     * Add a {@link Plane} to the scene.
     * @param distance The distance of the plane from the origin.
     * @param normal The normal of the plane.
     * @param color The color of the plane.
     * @param specularColor The specular color of the plane.
     * @param shininess The shininess of the plane.
     * @param reflexivity The reflexivity of the plane.
     * @param transparency The transparency of the plane.
     * @param refractionIndex The refraction index of the plane.
     */
    public void addPlane(
            final double distance,
            final Vec3d normal,
            final Color color, final Color specularColor,
            final double shininess, final double reflexivity,
            final double transparency, final double refractionIndex
    ) {
        this.objects.add(
                new Plane(
                    distance, normal, color, specularColor, shininess, reflexivity, transparency, refractionIndex
                )
        );
    }

    /**
     * Add a {@link Checkerboard} plane to the scene, with WHITE as primary color and BLACK as secondary.
     * @param distance The distance of the plane from the origin.
     * @param normal The normal of the plane.
     * @param specularColor The specular color of the plane.
     * @param shininess The shininess of the plane.
     * @param reflexivity The reflexivity of the plane.
     * @param transparency The transparency of the plane.
     * @param refractionIndex The refraction index of the plane.
     */
    public void addCheckerboard(
            final double distance,
            final Vec3d normal,
            final Color specularColor,
            final double shininess, final double reflexivity,
            final double transparency, final double refractionIndex
    ) {
        this.objects.add(
                new Checkerboard(
                    distance, normal, specularColor, shininess, reflexivity, transparency, refractionIndex
                )
        );
    }

    /**
     * Add a {@link Checkerboard} plane to the scene.
     * @param distance The distance of the plane from the origin.
     * @param normal The normal of the plane.
     * @param primary The color of the primary squares.
     * @param secondary The color of the secondary squares.
     * @param specularColor The color of the specular highlights.
     * @param shininess The shininess of the specular highlights.
     * @param reflexivity The reflexivity of the material.
     * @param transparency The transparency of the material.
     * @param refractionIndex The refraction index of the material.
     */
    public void addCheckerboard(
            final double distance,
            final Vec3d normal,
            final Color primary, final Color secondary, final Color specularColor,
            final double shininess, final double reflexivity,
            final double transparency, final double refractionIndex
    ){
        this.objects.add(
                new Checkerboard(
                    distance, normal, primary, secondary, specularColor, shininess, reflexivity, transparency, refractionIndex
                )
        );
    }

    /**
     * Compute the color of the pixel at the given coordinates.
     * @param P The origin of the ray.
     * @param v The direction of the ray.
     * @param depth The number of recursive calls.
     * @return The color of the pixel at the given coordinates.
     * @author Mathieu Niord
     */
    public Color getRayColor(Vec3d P, Vec3d v, int depth) {

        // If the depth is not 0, return (recursive stop)
        if (depth == 0) return ambientLight;

        boolean hit = false;
        double t = Double.MAX_VALUE;
        Solid nearestSolid = null;
        Color col;

        // Retrieve the nearest solid by computing the intersection of the ray with all the solids
        for (Solid solid : objects) {
            double delta = solid.getIntersection(P, v);
            if (delta > 0.0D && delta < t) { // Hit!
                hit = true;
                t = delta;
                nearestSolid = solid;
            }
        }

        // If there is no intersection, return the ambient light
        if (!hit) return ambientLight;

        final Vec3d I = P.add(v.scale(t));  // Intersection point
        Vec3d nI = nearestSolid.getNormal(I); // Normal at intersection point
        boolean inside = false;

        // Flip normal if the ray is coming from the inside of the solid
        if (v.dot(nI) > 0.0D) {
            nI =  nI.scale(-1.0D);
            inside = true;
        }

        col = (nearestSolid.getColor(I)).multiply(ambientLight);

        // Compute the new color of the object implementing shaders (Phong's model)
        computeShaders(I, v, nI, nearestSolid, col);

        if (nearestSolid.getReflection() > 0.0D || nearestSolid.getTransmission() > 0.0D) {

            // Reflection
            if (nearestSolid.getReflection() > 0.0D) {
                final Vec3d reflectDir = I.sub(nI.scale(2.0D * nI.dot(I)));
                Color reflectedColor =
                        getRayColor(I, reflectDir, depth - 1)
                        .scale(Math.max(Math.min(nearestSolid.getReflection(), 1.0D), 0.0D))
                ;

                col.setAdd(reflectedColor);
            }

            // Refraction
            if (nearestSolid.getTransmission() > 0.0D && nearestSolid.getRefractionIndex() > 0.0D) {

                final double eta = (inside) ? nearestSolid.getRefractionIndex() : (1.0D / nearestSolid.getRefractionIndex());
                final double c1 = (nI.scale(-1.0D)).dot(v);
                final double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1));
                final Vec3d refractDir = v.scale(eta).add(nI.scale(eta * c1 - c2));
                refractDir.setNormalize();

                Color refractedColor =
                        getRayColor(I, refractDir, depth - 1)
                        .scale(Math.max(Math.min(nearestSolid.getTransmission(), 1.0D), 0.0D))
                ;

                col.setAdd(refractedColor);
            }
        }

        return col;
    }

    /**
     * Compute the shaders of the given intersection point thanks the Phong's model
     * @param I intersection point {@link Vec3d}
     * @param v direction of the ray {@link Vec3d}
     * @param nearest solid intersected {@link Solid}
     * @author Mathieu Niord
     */
    private void computeShaders(Vec3d I, Vec3d v, Vec3d normal, Solid nearest, Color color) {

        // Shaders (Phong's model)
        for (Light light : lights) {

            boolean visible = (light.getIntensity() > 0.0D);
            Vec3d IS = light.getDirection(I); // Vector from intersection point to light source

            // Check if the light source is visible from the intersection point
            Iterator<Solid> iterator = objects.iterator();
            while (visible && iterator.hasNext()) {
                Solid neighbor = iterator.next();
                double deltaObj = neighbor.getIntersection(I, IS);   // Intersection with the light source
                visible = !(deltaObj > 0.0D && deltaObj < 1.0D);    // Visibility test
            }

            if (visible) {

                IS.setNormalize();              // Normalized vector from intersection point to light source
                Vec3d view = v.normalize();     // Normalized vector from intersection point to camera

                final double weight = Math.max(normal.dot(IS), 0.0D);
                final Vec3d r = IS.sub(normal.scale(weight * 2.0D));

                // Computation of the diffuse color
                Color diff = light.getDiffuseLight()
                        .multiply(nearest.getColor(I))
                        .scale(weight)
                        .scale(Math.min(light.getIntensity(), 1.0D));

                // Computation of the specular color
                Color spec = light.getSpecularLight()
                        .multiply(nearest.getSpecular())
                        .scale(Math.pow(Math.max(r.dot(view), 0.0D), nearest.getShininess()))
                        .scale(Math.min(light.getIntensity(), 1.0D));

                // The final color is the sum of the diffuse and specular colors
                color.setAdd(diff.add(spec));
            }
        }
    }

}
