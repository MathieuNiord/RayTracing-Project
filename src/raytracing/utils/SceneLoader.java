package raytracing.utils;

import raytracing.maths.Vec3d;
import raytracing.rendering.Light;
import raytracing.rendering.Scene;

/**
 * Loads a scene from an id.
 * @author Mathieu Niord
 */
public class SceneLoader {

    public Scene scene;

    public SceneLoader(final int sceneId) {

        this.scene = new Scene();

        switch (sceneId) {
            case 1:
                constructScene01();
            break;
            case 2:
                constructScene02();
            break;
            case 3:
                constructScene03();
            break;
            default:
                constructDefaultScene();
            break;
        }
    }

    private void constructDefaultScene() {
		
        // LIGHT
        scene.addLight(new Light(new Vec3d(0.0D, 800.0D, 0.0D), Color.LIGHT_GRAY, Color.WHITE, 1.0D));

        // PLANE
        scene.addCheckerboard(
                100.0D, new Vec3d(0.0D, 1.0D, 0.0D),
                Color.LIGHT_GRAY, 1000.0D, 0.3D,
                0.0D, 1.0D
        );

        // SPHERES
        scene.addSphere(
                100.0D, new Vec3d(-220.0D, 0.0D, -800.0D),
                Color.BLUE, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Blue

        scene.addSphere(
                100.0D, new Vec3d(0.0D, 0.0D, -800.0D),
                Color.GREEN, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Green

        scene.addSphere(
                100.0D, new Vec3d(220.0D, 0.0D, -800.0D),
                Color.RED, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Red
    }

    private void constructScene01() {
		
        // LIGHT
        scene.addLight(new Light(new Vec3d(0.0D, 800.0D, 0.0D), Color.LIGHT_GRAY, Color.WHITE, 0.7D));

        // PLANE
        scene.addCheckerboard(
                100.0D, new Vec3d(0.0D, 1.0D, 0.0D),
                Color.LIGHT_GRAY,
                1000.0D, 0.3D,
                0.0D, 1.0D
        );

        // SPHERES
        scene.addSphere(
                100.0D, new Vec3d(-220.0D, 0.0D, -800.0D),
                Color.BLUE, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Blue

        scene.addSphere(
                30.0D, new Vec3d(-300.0D, -70.0D, -680.0D),
                Color.CYAN, Color.LIGHT_GRAY,
                Double.MAX_VALUE,0.0D,
                0.0D, 0.0D
        ); // Cyan

        scene.addSphere(
                40.0D, new Vec3d(-360.0D, -55.0D, -690.0D),
                Color.YELLOW, Color.LIGHT_GRAY,
                Double.MAX_VALUE, 0.2D,
                0.6D, 1.0D
        ); // Yellow

        scene.addSphere(
                100.0D, new Vec3d(0.0D, 0.0D, -800.0D),
                Color.GREEN, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Green

        scene.addSphere(
                70.0D, new Vec3d(0.0D, 250.0D, -1000.0D),
                new Color(152F, 155F, 160F), Color.LIGHT_GRAY,
                1.0D, 1.0D,
                0.0D, 2.0D
        ); // Chrome

        scene.addSphere(
                100.0D, new Vec3d(220.0D, 0.0D, -800.0D),
                Color.RED, Color.WHITE,
                1000.0D, 0.6D,
                0.0D, 1.0D
        ); // Red
    }

    private void constructScene02() {

        // LIGHT
        scene.addLight(new Light(new Vec3d(-300.0D, 300.0D, -100.0D), Color.LIGHT_GRAY, Color.WHITE, 0.9D));


        // PLANES

        scene.addPlane(
                1000.0D, new Vec3d(0.32D, 0.0D, 1.0D),
                Color.DARK_GRAY, Color.DARK_GRAY,
                1.0D, 0.1D,
                0.0D, 0.0D
        );

        scene.addPlane(
                800.0D, new Vec3d(-0.8D, 0.0D, 0.6D),
                Color.DARK_GRAY, Color.DARK_GRAY,
                1000.0D, 0.1D,
                0.0D, 0.0D
        );

        scene.addCheckerboard(
                100.0D, new Vec3d(0.0D, 1.0D, 0.0D),
                Color.GRAY, Color.DARK_GRAY, Color.DARK_GRAY,
                1000.0D, 0.3D,
                0.0D, 0.0D
        );


        // SPHERES

        scene.addSphere(
                70.0D, new Vec3d(-50.0D, -30.0D, -400.0D),
                Color.RED, Color.LIGHT_GRAY,
                5.0D, 0.0D,
                0.0D, 0.0D
        ); // Red

        scene.addSphere(
                30.0D, new Vec3d(-0D, -70.0D, -315.0D),
                Color.DARK_GREEN, Color.LIGHT_GRAY,
                300.0D, 0.2D,
                1.0D, 1.5D
        ); // Green

        scene.addSphere(
                48.0D, new Vec3d(65.0D, -52.0D, -368.0D),
                Color.DARK_BLUE, Color.WHITE,
                300.0D, 0.2D,
                1.0D, 1.5D
        ); // Blue

        scene.addSphere(
                30.0D, new Vec3d(350.0D, -70.0D, -780.0D),
                Color.ORANGE, Color.LIGHT_GRAY,
                30.0D, 0.0D,
                0.0D, 0.0D
        ); // Orange

        scene.addSphere(
                20.0D, new Vec3d(375.D, -80.0D, -745.0D),
                Color.PINK, Color.LIGHT_GRAY,
                30.0D, 0.0D,
                0.0D, 0.0D
        ); // Pink

        scene.addSphere(
                35.0D, new Vec3d(-290.0D, -65.0D, -870.0D),
                Color.LIMEGREEN, Color.LIGHT_GRAY,
                30.0D, 0.0D,
                0.0D, 0.0D
        ); // Lime Green

        scene.addSphere(20.0D, new Vec3d(-350.0D, -82.0D, -897.0D),
                Color.LIGHT_BLUE, Color.LIGHT_GRAY,
                50.0D, 0.0D,
                0.0D, 0.0D
        ); // Light Blue
    }

    private void constructScene03() {

        scene.setAmbientLight(Color.GRAY);

        // LIGHT
        scene.addLight(new Light(new Vec3d(0.0D, 300.0D, -200.0D), Color.LIGHT_GRAY, Color.WHITE, 0.3D));


        // PLANES

        // left wall
        scene.addPlane(
                500.0D, new Vec3d(1.0D, 0.0D, 0.0D),
                Color.RED, Color.LIGHT_GRAY,
                100.0D, 0.0D,
                0.0D, 0.0D
        );

        // right wall
        scene.addPlane(
                500.0D, new Vec3d(-1.0D, 0.0D, 0.0D),
                Color.LIGHT_BLUE, Color.LIGHT_GRAY,
                100.0D, 0.0D,
                0.0D, 0.0D
        );

        // back
        scene.addPlane(
                350.0D, new Vec3d(0.0D, 0.0D, 1.0D),
                Color.WHITE, Color.LIGHT_GRAY,
                100.0D, 0.0D,
                0.0D, 1.0D
        );

        // ceiling
        scene.addPlane(
                500.0D, new Vec3d(0.0D, -1.0D, 0.0D),
                Color.WHITE, Color.LIGHT_GRAY,
                100.0D, 0.0D,
                0.0D, 0.0D
        );

        // floor
        scene.addPlane(
                500.0D, new Vec3d(0.0D, 1.0D, 0.0D),
                Color.YELLOW, Color.LIGHT_GRAY,
                10.0D, 0.0D,
                0.0D, 0.0D
        );


        // SPHERES

        // Big at center
        scene.addSphere(
                100.0D, new Vec3d(0.0D, 0.0D, -200.0D),
                Color.BLACK, Color.LIGHT_GRAY,
                10.0D, 0.0D,
                1.0D, 1.3D
        );

        // Small at left
        scene.addSphere(
                25.0D, new Vec3d(-250.0D, 0.0D, -300.0D),
                Color.BLACK, Color.LIGHT_GRAY,
                10.0D, 0.0D,
                1.0D, 1.3D
        );

        // Small at right
        scene.addSphere(
                25.0D, new Vec3d(250.0D, 0.0D, -300.0D),
                Color.BLACK, Color.LIGHT_GRAY, 10.0D,
                0.0D, 1.0D, 1.3D
        );

        // Small at top
        scene.addSphere(
                25.0D, new Vec3d(0.0D, 250.0D, -280.0D),
                Color.BLACK, Color.LIGHT_GRAY,
                10.0D, 1.0D,
                0.2D, 1.3D
        );
    }

}
