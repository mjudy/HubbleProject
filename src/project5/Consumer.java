package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Consumer implements Runnable
{
    private Buffer buff;
    private int n;
    public Consumer(Buffer buffer, int n)
    {
        buff = buffer;
        this.n = n;
    }
    public void run()
    {

    }
}
