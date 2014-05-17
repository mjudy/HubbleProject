package project5;

/**
 * @author theghv
 * @version 1.0 Date: 5/16/14 Time: 6:26 PM
 */
public class ThresholdSort
{
    public static int[] mergeSort(int[] array, int t)
    {
        mergeSort(array, t, 0, array.length - 1);
        return array;
    }

    private static void mergeSort(int[] array, int t, int min, int max)
    {
        if(max - min <= t)
        {
            insertionSort(array, min, max);
        }
        else
        {
            int mid = (min + max) / 2;
            SortAction left = new SortAction(array, t, min, mid);
            SortAction right = new SortAction(array, t, mid + 1, max);
            Thread leftThread = new Thread(left);
            Thread rightThread = new Thread(right);

            leftThread.run();
            rightThread.run();

            while(true)
            {
                try
                {
                    leftThread.join();
                    break;
                }
                catch (InterruptedException e)
                {
                }
            }

            while(true)
            {
                try
                {
                    rightThread.join();
                    break;
                } catch (InterruptedException e)
                {
                }
            }

            merge(array, min, mid, max);
        }
    }

    private  static void merge(int[] array, int min, int mid, int max)
    {
        int[] a = new int[max - min + 1];

        for(int i = 0, left = min, right = mid + 1; i < a.length; i++, left++, right++)
        {
            if(left == mid + 1)
            {
                a[i] = array[right];
                left--;
            }
            else if(right == max + 1)
            {
                a[i] = array[left];
                right--;
            }
            else if(array[left] < array[right])
            {
                a[i] = array[left];
                right--;
            }
            else
            {
                a[i] = array[right];
                left--;
            }
        }

        for(int i = 0, m = min; i < a.length; i++, m++)
        {
            array[m] = a[i];
        }
    }

    private synchronized static void insertionSort(int[] array, int min, int max)
    {
        for(int i = min; i <= max; i++)
        {
            for(int j = i; j <= max; j++)
            {
                if(array[j] < array[i])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private static class SortAction implements Runnable
    {
        private int[] array;
        private int t, min, max;

        public SortAction(int[] array, int t, int min, int max)
        {
            this.array = array;
            this.t = t;
            this.min = min;
            this.max = max;
        }

        @Override
        public void run()
        {
            ThresholdSort.mergeSort(array, t, min, max);
        }
    }
}
