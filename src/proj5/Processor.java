package proj5;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Defines a data processing class for the satellite simulation. This class creates a gray scale image from the generated
 * random values that have been normalized between 0 and 255.
 *
 * @author Mark Judy
 * @version 1.0 Date: 5/16/14 Time: 5:58 PM
 */
public class Processor
{
    private Buffer b2;
    private int n, t;
    private int[] data;
    private int[] normData;
    private String path;
    private long mergeSortTime;

    /**
     * Creates a new instance of the processor with the image size, sorting threshold, and received data buffer.
     *
     * @param b2 The buffer containing received data.
     * @param t The sorting threshold.
     * @param n The image side dimension.
     */
    public Processor(Buffer b2, int t, int n)
    {
        this.b2 = b2;
        this.t = t;
        this.n = n;
    }

    /**
     * Sorts the gathered data, normalizes the values, and then creates a new image based on the data.
     */
    public void processData()
    {
        try
        {
            long startTime = System.currentTimeMillis();
            data = ThresholdSort.mergeSort(b2.toArray(), t);
            mergeSortTime = System.currentTimeMillis() - startTime;

            normData = new int[data.length];
            normalize();

            // -- USE THIS FOR *NIX --
//            path = getClass().getClassLoader().getResource(".").getPath();
//            path += String.format("output_N%d_T%d.png", n, t);
//            path = path.replaceAll("bin/", "images/");

            // -- USE THIS FOR WINDBLOWS --
            path = String.format("images/output_N%d_T%d.png", n, t);
            File file = new File(path);

            if(file.exists())
            {
                file.delete();
            }

            BufferedImage image = new BufferedImage(n, n, BufferedImage.TYPE_BYTE_GRAY);
            WritableRaster raster = image.getRaster();

            int index = 0;
            for(int row = 0; row < image.getHeight(); row++)
            {
                for(int col = 0; col < image.getHeight(); col++)
                {
                    raster.setSample(col, row, 0, normData[index++]);
                }
            }

            ImageIO.write(image, "png", file);
        }
        catch (IOException e)
        {
            System.err.println("I couldn't deal with that file you were looking for.");
            e.printStackTrace();
        }
    }

    /**
     * Normalizes the gathered data to values between 0 and 255.
     */
    private void normalize()
    {
        int index = 0;
        for(int i : data)
        {
            int v = (int) ((i * 255.0) / 4096.0);
            normData[index] = v;
            index++;
        }
    }

    /**
     * Gets the file path of the created image.
     *
     * @return the file path of the created image.
     */
    public String getFilePath()
    {
        return path;
    }

    /**
     * Gets the elapsed time of the most recent sorting operation in milliseconds..
     *
     * @return the elapsed time of the most recent sorting operation in milliseconds.
     */
    public long getMergeSortTime()
    {
        return mergeSortTime;
    }
}
