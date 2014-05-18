package project5;

/**
 * Defines a data receiver for this satellite simulation.
 *
 * @author Mark Judy
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Receiver implements Runnable
{
    private Buffer b1;
    private Buffer b2;
    private int n, t;
    private Processor proc;

    /**
     * Creates a new receiver for use in the satellite simulation.
     *
     * @param b1 The satellite's buffer that stores created random numbers.
     * @param b2 The receiving buffer that stores N^2 values to create an image from.
     * @param n The current size of N which defines the size of the generated image.
     * @param t The current threshold value for choosing insertion sort or merge sort.
     */
    public Receiver(Buffer b1, Buffer b2, int n, int t)
    {
        this.b1 = b1;
        this.b2 = b2;
        this.n = n;
        this.t = t;
        proc = new Processor(b2, t, n);
    }

    /**
     * Waits for data to be collected by the satellite's buffer before processing a chunk of data and creating an image.
     */
    @Override
    public void run()
    {
        try
        {
            while(b1.size() < (n * n))
            {
                Thread.sleep(1000);
            }

            boolean isRunning;
            do
            {
                isRunning = b2.add(b1.remove());
            } while(isRunning);

            proc.processData();
        }
        catch (InterruptedException e)
        {
            System.err.println("Receiver Interrupted!");
            e.printStackTrace();
        }
    }

    /**
     * Gets the file path of the created image for reporting.
     *
     * @return The image's file path.
     */
    public String getFilePath()
    {
        return proc.getFilePath();
    }

    /**
     * Gets the elapsed time of the last sort for reporting.
     *
     * @return The last sort's elapsed time.
     */
    public long getMergeSortTime()
    {
        return proc.getMergeSortTime();
    }
}
