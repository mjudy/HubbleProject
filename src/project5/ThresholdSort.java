package project5;

import java.util.Arrays;

/**
 * @author theghv
 * @version 1.0 Date: 5/16/14 Time: 6:26 PM
 */
public class ThresholdSort
{
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

    static void mergeSort(int[] array)
    {
        if (array.length > 1)
        {
            int q = array.length/2;

            int[] leftArray = Arrays.copyOfRange(array, 0, q);
            int[] rightArray = Arrays.copyOfRange(array,q,array.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array,leftArray,rightArray);
        }
    }

    static void merge(int[] array, int[] arrayL, int[] arrayR)
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
}
