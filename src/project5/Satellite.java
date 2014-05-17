package project5;

import java.util.Random;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Satellite implements Runnable
{
    /** The Buffer B1 as mentioned in the project document */
    private Buffer b1;

    private boolean isRunning;

    public Satellite(Buffer b1)
    {
        this.isRunning = true;
        this.b1 = b1;
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
            Random generator = new Random();

            while(isRunning)
            {
                int value = generator.nextInt(4096 + 1);

                while(!b1.add(value))
                    Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Satellite Interrupted!");
            e.printStackTrace();
        }
    }
}
