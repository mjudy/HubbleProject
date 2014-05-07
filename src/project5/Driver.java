package project5;

public class Driver
{

    public static void main(String[] args) {
        // write your code here
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available Memory: " + Runtime.getRuntime().freeMemory());

        for(int i = 8; i <= 11; i++)
        {
            int n = (int)Math.pow(2, i);
            Buffer b1 = new Buffer(2*n*n);

            for(int j = 1; i <=5; i++)
            {
                int t = (int)Math.pow(10, j);
                Producer sat = new Producer(b1, n, t);
                Consumer rec = new Consumer(b1, n, t);


            }
        }
    }
}
