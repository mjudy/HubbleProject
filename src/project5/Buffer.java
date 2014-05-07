package project5;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Buffer implements Runnable
{
    private ConcurrentLinkedQueue<Integer> list;
    private int limit;
    private boolean keepRunning;


    public Buffer(int n)
    {
        list = new ConcurrentLinkedQueue<Integer>();
        limit = n;
        keepRunning = true;
    }

    public void run()
    {

    }

    synchronized public void add(int newInt)
    {
        if(list.size() >= limit) return;
        list.add(newInt);
        notify();
    }

    synchronized public void waitForSpace()
    {
        try
        {
            while (list.size() >= limit) wait();
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
            while (limit == 0) wait();
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interrupted!");
        }
    }

    synchronized public boolean isFull()
    {
        return list.size() >= limit;
    }

    public int size()
    {
        return list.size();
    }
}
