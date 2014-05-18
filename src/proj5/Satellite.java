package proj5;

import java.util.Random;

/**
 * Defines data and methods for a satellite simulator. The simulator generates random numbers to simulate the satellite
 * collecting data during its mission.
 *
 * @author Mark Judy
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Satellite implements Runnable
{
    private Buffer b1;
    private Random rand;
    private boolean isRunning;

    /**
     * Creates an instance of this satellite simulator with a provided data buffer.
     * @param b1 The provided buffer to prevent data overflow.
     */
    public Satellite(Buffer b1)
    {
        this.isRunning = true;
        this.b1 = b1;
        rand = new Random(System.currentTimeMillis());
    }

    /**
     * Stops the satellite from generating more random numbers.
     */
    public void stop()
    {
        isRunning = false;
    }

    /**
     * Performs the generation of random numbers while the satellite's thread is active.
     */
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
