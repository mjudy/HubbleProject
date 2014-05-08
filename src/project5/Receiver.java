package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Receiver implements Runnable
{
    private Buffer b1, b2;
    private int n, t;
    public boolean keepRunning;

    public Receiver(Buffer b1, Buffer b2, int n, int t)
    {
        this.b1 = b1;
        this.b2 = b2;
        this.n = n;
        this.t = t;
    }

    public void run()
    {
        try
        {
            keepRunning = true;
            if(b1.size() == (n*n))
            {
                System.out.println("Hey hey hey!");
                while(!b1.isEmpty())
                {
                    b2.add(b1.remove());
                }
            }

            Thread.sleep(100);
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interrupted!");
        }
    }
}
