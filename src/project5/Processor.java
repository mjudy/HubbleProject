package project5;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
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

            File folder = new File("images");
            path = String.format("images/output_N%d_T%d.png", n, t);
            File file = new File(path);

            if(!folder.exists())
            {
                folder.mkdir();
            }

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
                    raster.setPixel(col, row, new int[] {normData[index++]});
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

    public String getFilePath()
    {
        return path;
    }

    public long getMergeSortTime() {
        return mergeSortTime;
    }
}
