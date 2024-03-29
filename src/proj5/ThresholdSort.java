package proj5;

import java.util.Arrays;

/**
 * This class determines whether to use an insertion sort or merge sort based on a given threshold value.
 *
 * @author Mark Judy
 * @version 1.0 Date: 5/16/14 Time: 6:26 PM
 */
public class ThresholdSort
{
    /**
     * Chooses insertion sort if the given unsorted array has fewer values than the threshold, or merge sort if there
     * are more values than the threshold.
     *
     * @param array The array of values to sort
     * @param t The threshold value
     * @return The sorted array.
     */
    public static int[] mergeSort(int[] array, int t)
    {
        if(array.length <= t)
        {
            insertionSort(array, 0, array.length - 1);
        }
        else
        {
            mergeSort(array);
        }

        return array;
    }

    /**
     * Sorts a given array using merge sort.
     * @param array The array to sort
     */
    private static void mergeSort(int[] array)
    {
        if (array.length > 1)
        {
            int q = array.length/2;

            int[] leftArray = Arrays.copyOfRange(array, 0, q);
            int[] rightArray = Arrays.copyOfRange(array, q, array.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array,leftArray,rightArray);
        }
    }

    /**
     * Merges partial arrays generated by the merge sort algorithm.
     *
     * @param array The final array.
     * @param arrayL The sorted left array to merge into the final array.
     * @param arrayR The sorted right array to merge into the final array.
     */
    private static void merge(int[] array, int[] arrayL, int[] arrayR)
    {
        int total = arrayL.length + arrayR.length;
        int index, indexL, indexR;
        index = indexL = indexR = 0;
        while (index < total)
        {
            if ((indexL < arrayL.length) && (indexR<arrayR.length))
            {
                if (arrayL[indexL] < arrayR[indexR])
                {
                    array[index] = arrayL[indexL];
                    index++;
                    indexL++;
                }
                else
                {
                    array[index] = arrayR[indexR];
                    index++;
                    indexR++;
                }
            }
            else
            {
                if (indexL >= arrayL.length)
                {
                    while (indexR < arrayR.length)
                    {
                        array[index] = arrayR[indexR];
                        index++;
                        indexR++;
                    }
                }
                if (indexR >= arrayR.length)
                {
                    while (indexL < arrayL.length)
                    {
                        array[index] = arrayL[indexL];
                        indexL++;
                        index++;
                    }
                }
            }
        }
    }

    /**
     * Performs an insertion sort over the specified range in the array.
     *
     * @param array The array to sort.
     * @param min The minimum index of the array to sort.
     * @param max The maximum index of the array to sort.
     */
    private static void insertionSort(int[] array, int min, int max)
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
}
