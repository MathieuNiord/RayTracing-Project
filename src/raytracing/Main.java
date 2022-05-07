package raytracing;

import raytracing.maths.Vec3d;
import raytracing.rendering.Scene;
import raytracing.utils.*;

import java.io.IOException;

/**
 * Main class of the raytracing program.
 * @author Niord Mathieu
 */
public class Main extends JavaTga {

    private static int width = 1920, height = 1080, depth = 5, scene = 0;
    private static double zoom = -0.5D;
    private static String outputName = "output.tga";

    static class HelpException extends Exception {
        public HelpException() {
            super("");
        }
    }

    public static void main(String[] args) {

        try {
            parseCommandLine(args);
        }
        catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("\nCommand Line Exception: " + e.getMessage());
            printUsage();
            System.exit(1);
        }
        catch (HelpException e) {
            printUsage();
            System.exit(0);
        }

        final int min = Math.min(width, height);
        byte[] buffer = new byte[3 * width * height];

        SceneLoader loader = new SceneLoader(scene);
        Scene scene = loader.scene;

        for (int row = 0; row < height; ++row)
            for (int col = 0; col < width; ++col) {

                final int index = 3 * ((row * width) + col);

                double x = (col - width / 2.0D) / min;
                double y = (row - height / 2.0D) / min;
                Vec3d dir = new Vec3d(x, y, zoom);

                Color color = scene.getRayColor(new Vec3d(0, 0, 0), dir, depth);

                buffer[index] = (byte)color.getBlue();      // Blue
                buffer[index+1] = (byte)color.getGreen();   // Green
                buffer[index+2] = (byte)color.getRed();     // Red
            }

        try { saveTGA(outputName, buffer, width, height); }
        catch (IOException e) { System.err.println("TGA file not created :"+e); }
    }

    /**
     * Parse the command line arguments.
     * @param args The command line arguments.
     * @throws IllegalArgumentException If an argument is invalid.
     * @throws HelpException If the help argument is given.
     */
    private static void parseCommandLine(String[] args) throws IllegalArgumentException, ArrayIndexOutOfBoundsException, HelpException {

        for (int i = 0; i < args.length; ++i) {

            switch (args[i]) {

                case "-w" :
                    try { width = Integer.parseInt(args[++i]); }
                    catch (NumberFormatException e) {
                        throw new NumberFormatException("The [width] option must be an integer : " + args[i]);
                    }
					catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException("The [width] option must be followed by an integer");
                    }
                break;

                case "-h" :
                    try { height = Integer.parseInt(args[++i]); }
                    catch (NumberFormatException e) {
                        throw new NumberFormatException("The [height] option must be an integer : " + args[i]);
                    }
					catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException("The [height] option must be followed by an integer");
                    }
                break;

                case "-d" :
                    try { depth = Integer.parseInt(args[++i]); }
                    catch (NumberFormatException e) {
                        throw new NumberFormatException("The [depth] option must be an integer : " + args[i]);
                    }
					catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException("The [depth] option must be followed by an integer");
                    }
                break;

                case "-z" :
                    try { zoom = Double.parseDouble(args[++i]); }
                    catch (NumberFormatException e) {
                        throw new NumberFormatException("The [zoom] option must be a double : " + args[i]);
                    }
					catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException("The [zoom] option must be followed by a double value");
                    }
                break;

                case "-o" :
                    String output = args[++i];
                    if (output.endsWith(".tga")) outputName = args[++i];
                    else outputName = output + ".tga";
                break;

                case "-s" :
                    try { scene = Integer.parseInt(args[++i]); }
                    catch (NumberFormatException e) {
                        throw new NumberFormatException("The [scene] option must be an integer : " + args[i]);
                    }
					catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArrayIndexOutOfBoundsException("The [scene] option must be followed by an integer");
                    }
                break;

                case "--help" : throw new HelpException();

                default : throw new IllegalArgumentException("Unknown option : " + args[i]);
            }
        }
    }

    private static void printUsage() {
        System.out.println(
                "\nUsage : java raytracing.Main [options]"
                + "\n\nOptions :"
                + "\n\t-w <width>\t\tWidth of the output image."
                + "\n\t-h <height>\t\tHeight of the output image."
                + "\n\t-d <depth>\t\tMaximum ray depth."
                + "\n\t-z <zoom>\t\tZoom factor."
                + "\n\t-o <output>\t\tOutput file name."
                + "\n\t-s <scene>\t\tScene number."
                + "\n\t--help\t\t\tPrint this help."
                + "\n\nScenes :"
                + "\n\tDefault : Spheres on checker board."
                + "\n\t1 : Spheres on checker board (alternative)."
                + "\n\t2 : Spheres with refraction on checker board."
                + "\n\t3 : Cornell Box\n"
        );
    }

}
