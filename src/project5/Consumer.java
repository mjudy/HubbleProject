package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/6/14 Time: 10:09 PM
 */
public class Consumer implements Runnable
{
    private Buffer b1;
    private int n, t;
    public Consumer(Buffer b1, int n, int t)
    {
        this.b1 = b1;
        this.n = n;
        this.t = t;
    }
    public void run()
    {
        try
        {
            while(b1.size() <= (n*n))
            {
                wait();
            }
        }
        catch (InterruptedException ie)
        {
            System.out.println("Interrupted!");
        }
    }
}
