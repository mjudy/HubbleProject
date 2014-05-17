package project5;

import java.util.LinkedList;

/**
 * @author Mark Judy
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Buffer
{
    private volatile LinkedList<Integer> buffer;
    private volatile int limit;

    public Buffer(int limit)
    {
        buffer = new LinkedList<Integer>();
        this.limit = limit;
    }

    public synchronized boolean add(int num)
    {
        if(buffer.size() == limit)
        {
            return false;
        }
        else
        {
            buffer.add(num);
            return true;
        }
    }

    public synchronized int remove()
    {
        if(buffer.size() == 0)
        {
            throw new RuntimeException("Empty Buffer!");
        }
        return buffer.poll();
    }

    public int size()
    {
        return buffer.size();
    }

    public int[] toArray()
    {
        int[] arry = new int[buffer.size()];
        int index = 0;
        for(Integer i : buffer)
        {
            arry[index] = i;
            index++;
        }
        return arry;
    }
}
