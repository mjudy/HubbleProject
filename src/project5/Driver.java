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
            Thread bufThread = new Thread(b1);
            bufThread.start();

            //Start sat thread
            Satellite sat = new Satellite(b1, n);
            Thread satThread = new Thread(sat);
            satThread.start();

            for(int j = 1; i <=5; i++)
            {
                //Start receiver thread
                int t = (int)Math.pow(10, j);
                Receiver rec = new Receiver(b1, n, t);
                Thread recThread = new Thread(rec);
                recThread.start();

                //Do merge sort

                //Do image creation
            }
        }
    }
}
