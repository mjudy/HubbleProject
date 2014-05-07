package proj5;

import java.util.LinkedList;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Buffer implements Runnable
{
    private LinkedList<Integer> list;
    private int limit;
    private boolean keepRunning;


    public Buffer(int limit)
    {
        list = new LinkedList<Integer>();
        this.limit = limit;
    }
    public void run()
    {

    }


}
