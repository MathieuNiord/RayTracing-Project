package raytracing.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class JavaTga
{
    /**
     *
     * @param fOut : output file stream
     * @param n : short to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fOut, int n) throws IOException {
        fOut.write(n&255);
        fOut.write((n>>8)&255);
    }

    /**
     *
     * @param filename name of final TGA file
     * @param buffer buffer that contains the image. 3 bytes per pixel ordered this way : Blue, Green, Red
     * @param width Width of the image
     * @param height Height of the image
     * @throws FileNotFoundException If the file cannot be opened
     * @throws UnsupportedEncodingException If the file cannot be written
     * @throws IOException If the file cannot be written
     */
    protected static void saveTGA(String filename, byte[] buffer, int width, int height) throws IOException, UnsupportedEncodingException {

        FileOutputStream fOut = new FileOutputStream(filename);

        fOut.write(0); // Comment size, no comment
        fOut.write(0); // Colormap type: No colormap
        fOut.write(2); // Image type
        writeShort(fOut,0); // Origin
        writeShort(fOut,0); // Length
        fOut.write(0); // Depth
        writeShort(fOut,0); // X origin
        writeShort(fOut,0); // Y origin
        writeShort(fOut,width); // Width of the image
        writeShort(fOut,height); // Height of the image
        fOut.write(24); // Pixel size in bits (24bpp)
        fOut.write(0); // Descriptor

        /* Write the buffer */
        fOut.write(buffer);

        fOut.close();
    }
}