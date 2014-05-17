package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Receiver implements Runnable
{
    private Buffer b1;
    private Buffer b2;
    private int n, t;
    private Processor proc;

    public Receiver(Buffer b1, Buffer b2, int n, int t)
    {
        this.b1 = b1;
        this.b2 = b2;
        this.n = n;
        this.t = t;
        proc = new Processor(b2, t, n);
    }

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

    public String getRelativeFilePath()
    {
        return proc.getRelativeFilePath();
    }

    public long getMergeSortTime()
    {
        return proc.getMergeSortTime();
    }
}
