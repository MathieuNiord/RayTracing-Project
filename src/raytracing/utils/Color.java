package raytracing.utils;

/**
 * Colors and arithmetics of Colors.
 * @author Mathieu Niord
 */
public class Color {

    public static final Color BLACK         = new Color(0F, 0F, 0F);
    public static final Color WHITE         = new Color(255F, 255F, 255F);
    public static final Color GRAY          = new Color(128F, 128F, 128F);
    public static final Color DARK_GRAY     = new Color(30F, 30F, 30F);
    public static final Color LIGHT_GRAY    = new Color(192F, 192F, 192F);

    public static final Color BLUE          = new Color(255F, 0F, 0F);
    public static final Color RED           = new Color(38, 58, 190);
    public static final Color GREEN         = new Color(0F, 255F, 0F);

    public static final Color CYAN          = new Color(255F, 255F, 0F);
    public static final Color YELLOW        = new Color(100F, 200F, 230F);
    public static final Color MAGENTA       = new Color(255F, 0F, 255F);

    public final static Color LIMEGREEN     = new Color(116F, 174F, 77F);
    public final static Color DARK_GREEN    = new Color(20, 60, 5);
    public static final Color LIGHT_BLUE    = new Color(186F, 126F, 86F);
    public static final Color DARK_BLUE     = new Color(80, 50, 50);
    public static final Color ORANGE        = new Color(89, 153, 249);
    public static final Color PINK          = new Color(122F, 98F, 220F);

    private float blue, green, red;

    public Color() {
        this(0F, 0F, 0F);
    }

    public Color(float b, float g, float r) {
        this.blue = (b < 0F) ? 0F : Math.min(b, 255F);
        this.green = (g < 0F) ? 0F : Math.min(g, 255F);
        this.red = (r < 0F) ? 0F : Math.min(r, 255F);
    }

    public float getBlue() {
        return this.blue;
    }

    public float getGreen() {
        return this.green;
    }

    public float getRed() {
        return this.red;
    }

    // Multiply two colors together
    public Color multiply(Color other) {

        float b = (this.blue * other.blue) / 255F;
        float g = (this.green * other.green) / 255F;
        float r = (this.red * other.red) / 255F;

        return new Color(b, g, r);
    }

    /**
     * Multiply a color by a scalar (The scalar is handle as a float)
     * @param scalar The scalar value {@code double}
     * @return The scaled color {@code Color}
     */
    public Color scale(double scalar) {

        float b = Math.min((float)Math.floor(this.blue *  scalar), 255F);
        float g = Math.min((float)Math.floor(this.green * scalar), 255F);
        float r = Math.min((float)Math.floor(this.red *   scalar), 255F);

        return new Color(b, g, r);
    }

    // Add two colors together
    public Color add(Color other) {

        float b = Math.min(other.blue + this.blue, 255F);
        float g = Math.min(other.green + this.green, 255F);
        float r = Math.min(other.red + this.red, 255F);

        return new Color(b, g, r);
    }

    public void setAdd(Color other) {
        this.blue = Math.min(other.blue + this.blue, 255F);
        this.green = Math.min(other.green + this.green, 255F);
        this.red = Math.min(other.red + this.red, 255F);
    }

    // Subtract two colors
    public Color subtract(Color other) {

        float b = Math.max(this.blue - other.blue, 0F);
        float g = Math.max(this.green - other.green, 0F);
        float r = Math.max(this.red - other.red, 0F);

        return new Color(b, g, r);
    }
}
