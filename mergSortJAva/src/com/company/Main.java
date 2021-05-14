package com.company;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void merge(int tablica[], int start, int srodek, int koniec)
    {
        int tab_pom[] = new int[(koniec+1 - start)]; // utworzenie tablicy pomocniczej

        int i = start, j = srodek + 1, k = 0; // zmienne pomocnicze

        while (i <= srodek && j <= koniec)
        {
            if (tablica[j] < tablica[i])
            {
                tab_pom[k] = tablica[j];
                j++;
            }
            else
            {
                tab_pom[k] = tablica[i];
                i++;
            }
            k++;
        }

        if (i <= srodek)
        {
            while (i <= srodek)
            {
                tab_pom[k] = tablica[i];
                i++;
                k++;
            }
        }
        else
        {
            while (j <= koniec)
            {
                tab_pom[k] = tablica[j];
                j++;
                k++;
            }
        }

        for (i = 0; i <= koniec - start; i++)
            tablica[start + i] = tab_pom[i];


        //cout << endl;
        //for (i = start; i <= koniec; i++) // wypisanie posortowanej tablicy
        //cout << "tablica[" << i << "] = " << tablica[i] << endl;

    }

    public static void merge_sort(int tablica[], int start, int koniec)
    {

        int srodek;

        if (start != koniec)
        {
            srodek = (start + koniec) / 2;
            merge_sort(tablica, start, srodek);
            merge_sort(tablica, srodek + 1, koniec);
            merge(tablica, start, srodek, koniec);
        }
    }
    

    public static void main(String[] args) throws InterruptedException {
        //int arr[] = { 12, 11, 13, 5, 6, 7 };
        Random random=new Random();
        int size=40000000;
        int MAX_THREADS=2;
        int arr[]=new int[size];
        Thread arrayThreads[]=new Thread[MAX_THREADS];
        int poolSize = size / MAX_THREADS;
        System.gc();
        Runtime rt = Runtime.getRuntime();
        ExecutorService executor = Executors.newFixedThreadPool(4);


        long start1 = System.currentTimeMillis();
        for(int i=0;i<size;i++){
            arr[i]=random.nextInt(size);
        }
        long finish1 = System.currentTimeMillis();
        long timeElapsed = finish1 - start1;
        System.out.println("Making array time: "+timeElapsed);



        long start2 = System.currentTimeMillis();
        //MergeSort ob = new MergeSort();
        //executor.execute(()->ob.sort(arr,0,arr.length-1));
        //ob.sort(arr, 0, arr.length - 1);


        for (int i = 0; i < MAX_THREADS; i++)
        {
            int finalI = i;
            int finalI1 = i;
            arrayThreads[i]= new Thread(() -> merge_sort(arr, finalI1 *poolSize,(finalI1 +1)*poolSize -1));
            arrayThreads[i].start();



            //std::thread tmp(isPrimeMultiThreading,i * poolSize, (i + 1) * poolSize,i);
            //threadsVec[i]=std::move(tmp);
            //std::cout << "start: " << i * poolSize << " stop: " << (i + 1) * poolSize << std::endl;

        }


        for(int i=0;i<MAX_THREADS;i++){
            arrayThreads[i].join();
        }
        merge_sort(arr,0,size-1);


        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("Memory  MB usage: "+usedMB);
        System.out.println("Sorting array time: "+timeElapsed2);

        for(int i=0;i<50;i++){ System.out.print(arr[i]+" "); }


    }
}
