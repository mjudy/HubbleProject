package project5;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author theghv
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

    public Processor(Buffer b2, int t, int n)
    {
        this.b2 = b2;
        this.t = t;
        this.n = n;
    }

    public void processData()
    {
        try
        {
            long startTime = System.currentTimeMillis();
            data = ThresholdSort.mergeSort(b2.toArray(), t);
            mergeSortTime = System.currentTimeMillis() - startTime;

            normData = new int[data.length];
            normalize();

            File file = new File("images");

            if(!file.exists())
            {
                file.mkdir();
            }

            path = String.format("images/output_N%d_T%d.png", n, t);
            file = new File(path);

            if(file.exists())
            {
                file.delete();
            }

            BufferedImage image = new BufferedImage(n, n, BufferedImage.TYPE_BYTE_GRAY);
            WritableRaster raster = image.getRaster();

            for(int i = 0, iter = 0; i < image.getHeight(); i++)
            {
                for(int j = 0; j < image.getHeight(); j++, iter++)
                {
                    raster.setPixel(j, i, new int[] {normData[iter]});
                }
            }

            ImageIO.write(image, "png", file);
        }
        catch (IOException e)
        {
            System.err.println("Caught error...");
            e.printStackTrace();
        }
    }

    private void normalize()
    {
        int index = 0;
        for(int i : data)
        {
            int v = (int) Math.floor(i * (255.0 / 4096.0));
            normData[index] = v;
            index++;
        }
    }

    public String getRelativeFilePath()
    {
        return path;
    }

    public long getMergeSortTime() {
        return mergeSortTime;
    }
}
