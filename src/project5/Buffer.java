package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Buffer implements Runnable
{
    private int[] list;
    private int index;
    private boolean keepRunning;


    public Buffer(int n)
    {
        list = new int[n];
        index = 0;
        keepRunning = true;
    }

    public void run()
    {

    }

    synchronized public void add(int newInt)
    {
        if(index >= list.length) return;
        list[index++] = newInt;
        notify();
    }

    synchronized public void waitForSpace()
    {
        try
        {
            while (index >= list.length) wait();
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interrupted!");
        }
    }

    synchronized public void waitForData()
    {
        try
        {
            while (index == 0) wait();
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interrupted!");
        }
    }

    synchronized public boolean isFull()
    {
        return index >= list.length;
    }
}
