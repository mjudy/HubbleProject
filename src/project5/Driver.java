package project5;

/**
 * This class creates and runs all of the instances and threads for the satellite simulation.
 * It also prints a report of the program's status and the available processors and memory on the current system.
 */
public class Driver
{
    /**
     * Runs the satellite simulation.
     *
     * @param args command line arguments for this program.
     */
    public static void main(String[] args)
    {
        int count = 1;
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available Memory: " + Runtime.getRuntime().freeMemory() + "\n");

        try
        {
            //this goes to 11, that's one louder
            for(int i = 8; i <= 11; i++)
            {
                int n = (int) Math.pow(2, i);
                Buffer b1 = new Buffer(n * n * 2);
                Satellite satellite = new Satellite(b1);
                Thread satThread = new Thread(satellite);
                satThread.start();

                for(int j = 1; j <= 5; j++)
                {
                    int t = (int) Math.pow(10, j);
                    Buffer b2 = new Buffer(n * n);
                    Receiver rec = new Receiver(b1, b2, n, t);
                    Thread recThread = new Thread(rec);

                    recThread.start();
                    recThread.join();

                    System.out.println("Run #" + count++ + "; i=" + i + "; j=" + j + "; N=" + n + "; B1=" + (2 * n * n) + "; B2=" + (n * n) + "; T=" + t);
                    System.out.println("Sort Time: " + rec.getMergeSortTime() + "ms");
                    System.out.println("Image: " + rec.getFilePath() + "\n");
                }

                satellite.stop();
                satThread.join(10000);
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Driver Interrupted!");
            e.printStackTrace();
        }
        System.exit(0);
    }
}
