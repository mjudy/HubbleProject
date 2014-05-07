package project5;

import java.util.Random;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Producer implements Runnable
{
    private Buffer buff;
    private int n;
    private int randInt;
    private boolean keepRunning;
    Random rand;

    public Producer(Buffer buffer, int n)
    {
        buff = buffer;
        this.n = n;
        randInt = 0;
        rand = new Random(System.currentTimeMillis());
        keepRunning = true;
    }
    public void run()
    {
//        try
//        {
            for(int i = 0; i < n; i++)
            {
                randInt = rand.nextInt(4097);
                buff.waitForSpace();
                buff.add(randInt);
            }
//        }
//        catch (InterruptedException ie)
//        {
//            System.out.println("Interruption!");
//        }
    }
}
