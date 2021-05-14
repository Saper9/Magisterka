package com.company;

import java.util.Vector;
import java.lang.Thread;

public class Main {

    static int MAX_THREADS=6;
    static int MAX=1000000;
    static Vector primeNumbers = new Vector();
    public static Boolean isPrime(int n)
    {
        if (n <= 1)
            return false;


        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
    public static void isPrimeMultiThreading(int start, int end,int threadNumber)
    {

        System.out.println("Thread: "+Thread.currentThread().getName());
        for (int iter = start; iter < end; iter++)
        {
            if (isPrime(iter))
            {
                primeNumbers.addElement(iter);
            }
        }

    }
    public static void printPrime() {

        int iter = 0;

            for (int j = 0; j < primeNumbers.size(); j++)
            {
                //iter++;
                System.out.println(primeNumbers.elementAt(j));
            }

        //std::cout << "Prime numbers amount " << iter << std::endl;
    }
    public static void main(String[] args) throws InterruptedException {
        Thread arrayThreads[]=new Thread[MAX_THREADS];
        int poolSize = MAX / MAX_THREADS;
        System.gc();
        Runtime rt = Runtime.getRuntime();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREADS; i++)
        {
            int finalI = i;
            arrayThreads[i]= new Thread(() -> isPrimeMultiThreading(finalI * poolSize,(finalI + 1) * poolSize, finalI));
            arrayThreads[i].start();
            //std::thread tmp(isPrimeMultiThreading,i * poolSize, (i + 1) * poolSize,i);
            //threadsVec[i]=std::move(tmp);
            //std::cout << "start: " << i * poolSize << " stop: " << (i + 1) * poolSize << std::endl;

        }

        for(int i=0;i<MAX_THREADS;i++){
            arrayThreads[i].join();
        }

        /*


        long start1 = System.currentTimeMillis();
        /*
        for (int iter = 1; iter < MAX; iter++)
        {
            if (isPrime(iter)) {
                primeNumbers.addElement(iter);
            }
        }
        */

        long finish1 = System.currentTimeMillis();
        long timeElapsed = finish1 - start1;
        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("Memory  MB usage: "+usedMB);

        System.out.println("Searching Prime numbers time: "+timeElapsed);
        System.out.println("Prime numbers amount: "+primeNumbers.size());
        //printPrime();
    }
}
