package com.company;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //int arr[] = { 12, 11, 13, 5, 6, 7 };
        Random random=new Random();
        int size=20000000;
        int arr[]=new int[size];
        System.gc();
        Runtime rt = Runtime.getRuntime();
        //ExecutorService executor = Executors.newFixedThreadPool(4);

        //logger.information(this, "memory usage" + usedMB);
        long start1 = System.currentTimeMillis();
        for(int i=0;i<size;i++){
            arr[i]=random.nextInt(size);
        }
        long finish1 = System.currentTimeMillis();
        long timeElapsed = finish1 - start1;

        System.out.println("Making array time: "+timeElapsed);



        long start2 = System.currentTimeMillis();
        MergeSort ob = new MergeSort();
        //executor.execute(()->ob.sort(arr,0,arr.length-1));
        ob.sort(arr, 0, arr.length - 1);
        //qsort(arr,0,arr.length-1);

        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        //long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("Memory  MB usage: "+MergeSort.memoryUsed);
        System.out.println("Sorting array time: "+timeElapsed2);

        //MergeSort.printArray(arr);

    }
}
class MergeSort
{
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
static long memoryUsed=0;
    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
            Runtime rt = Runtime.getRuntime();
            long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
            if(usedMB>memoryUsed){
                memoryUsed=(rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
            }
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {

        System.out.println("10 elements of sorted array:");
        for (int i = 0; i < 10; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


}