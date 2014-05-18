package project5;

import java.util.LinkedList;

/**
 * Defines a buffer for the hubble project using a LinkedList implementation.
 *
 * @author Mark Judy
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Buffer
{
    private volatile LinkedList<Integer> buffer;
    private volatile int limit;

    /**
     * Creates a new buffer with a maximum size controlled by the given limit.
     *
     * @param limit The maximum size of the new buffer.
     */
    public Buffer(int limit)
    {
        buffer = new LinkedList<Integer>();
        this.limit = limit;
    }

    /**
     * Adds a new number to the buffer if there is room.
     *
     * @param num The new number to add to the buffer.
     * @return Returns true when a number is added successfully and false if the buffer is full.
     */
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

    /**
     * Removes the first number from the buffer if the buffer is populated. Throws a RuntimeException if the buffer
     * is empty when remove is called.
     *
     * @return The value removed from the front of the buffer.
     */
    public synchronized int remove()
    {
        if(buffer.size() == 0)
        {
            throw new RuntimeException("Empty Buffer!");
        }
        return buffer.poll();
    }

    /**
     * Gets the current size of the buffer.
     *
     * @return The number of elements stored in the buffer.
     */
    public int size()
    {
        return buffer.size();
    }

    /**
     * Converts the buffer to an array.
     *
     * @return the array created from this buffer.
     */
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
