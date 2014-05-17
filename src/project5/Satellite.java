package project5;

import java.util.Random;

/**
 * @author Mark Judy
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Satellite implements Runnable
{
    private Buffer b1;
    private Random rand;
    private boolean isRunning;

    public Satellite(Buffer b1)
    {
        this.isRunning = true;
        this.b1 = b1;
        rand = new Random(System.currentTimeMillis());
    }

    public void stop()
    {
        isRunning = false;
    }

    @Override
    public void run()
    {
        try
        {
            while(isRunning)
            {
                while(!b1.add(rand.nextInt(4097)))
                {
                    Thread.sleep(1000);
                }
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Satellite Interrupted!");
            e.printStackTrace();
        }
    }
}
