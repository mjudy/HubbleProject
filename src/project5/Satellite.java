package project5;

import java.util.Random;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Satellite implements Runnable
{
    private Buffer b1;
    private int n;
    private int randInt;
    private int count;
    public boolean keepRunning;
    Random rand;

    public Satellite(Buffer b1, int n)
    {
        this.b1 = b1;
        this.n = n;
        randInt = 0;
        count = 0;
        rand = new Random(System.currentTimeMillis());
        keepRunning = true;
    }

    public void run()
    {
        try
        {
            keepRunning = true;

            if(b1.isFull()) wait();
            while(!b1.isFull())
            {
                randInt = rand.nextInt(4097);
                b1.add(randInt);
//                count++;
                Thread.sleep(100);
                System.out.println(b1.size() + ": " + randInt);
            }
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interruption!");
        }
    }


}
