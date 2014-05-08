package project5;

public class Driver
{

    public static void main(String[] args) {
        // write your code here
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available Memory: " + Runtime.getRuntime().freeMemory());

        //It goes to 11. That's one louder.
        for(int i = 8; i <= 9; i++)
        {
            int n = (int)Math.pow(2, i);
            Buffer b1 = new Buffer(2*(n*n));
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
                Buffer b2 = new Buffer(n*n);
                Receiver rec = new Receiver(b1, b2, n, t);
                Thread recThread = new Thread(rec);

                recThread.start();

                try
                {
                    satThread.join();

                    b1.keepRunning = false;
                    rec.keepRunning = false;
                }
                catch (InterruptedException ie)
                {
                    System.out.println("Interrupted!");
                }


                //Do merge sort

                //Do image creation

                //Print output
                int count = 1;
                System.out.println("Run #" + count++ + "; i=" + i + "; j=" + j + "; N=" + n + "; B1=" + (2*n*n) + "; B2=" + (n*n) + "; T=" + t);
            }
        }
    }
}
