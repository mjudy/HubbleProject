package project5;

import java.util.Random;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Producer implements Runnable
{
    private Buffer b1;
    private int n, t;
    private int randInt;
    private int count;
    private boolean keepRunning;
    Random rand;

    public Producer(Buffer b1, int n, int t)
    {
        this.b1 = b1;
        this.n = n;
        this.t = t;
        randInt = 0;
        count = 0;
        rand = new Random(System.currentTimeMillis());
        keepRunning = true;
    }
    public void run()
    {
        try
        {
            if(b1.isFull()) wait();
            while(count <= t)
            {
                randInt = rand.nextInt(4097);
                b1.add(randInt);
                System.out.println(count + ": " + randInt);
                b1.waitForSpace();
                count++;
            }
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interruption!");
        }
    }
}
