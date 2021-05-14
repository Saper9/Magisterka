
import time
import tracemalloc
import threading
from multiprocessing import Process, Value, Array
import os
from multiprocessing import Pool

def isPrime(n):
    if (n <= 1):
        return False

    for i in range(2,n):
        if n%i==0:
            return False
    return True
primeNumber=[]

def isPrimeMultiThreading(start,end):
    primeVEC=[]
    for iter in range(start,end):
        if(isPrime(iter)):
            primeVEC.append(iter)
    return primeVEC




MAX_THREADS=1
if __name__ == '__main__':

    tabSizes=[100000,500000,1000000]
    for MAX in tabSizes:
        poolSize = MAX / MAX_THREADS;
        print("Size :", MAX)
        tracemalloc.start()
        time1 = time.time()

        

        #threadTab=[]
        threadTab = [None]*MAX_THREADS
        amountofpoolsX = []
        numbeorThread=[]
        for i in range(0, MAX_THREADS):
            amountofpoolsX.append([int(i * poolSize),int((i + 1) * poolSize)])

        p=Pool(processes=MAX_THREADS)
        result=p.starmap(isPrimeMultiThreading,(amountofpoolsX))
        p.close()
        p.join()


        #for i in range(1, MAX):
        #    if isPrime(i) == True:
        #        primeNumber.append(i)


        time2 = time.time()
        tmptime = time2 - time1
        print("Geting prime time:: ", tmptime)
        current, peak = tracemalloc.get_traced_memory()
        print(f"Current memory usage is {current / 10 ** 6}MB; Peak was {peak / 10 ** 6}MB")
        tracemalloc.stop()
        #print("Prime numbers: ", result)
        print("number of primes: ", len(result[0]))


    #
    #print("number of primes: ",len(primeNumber))
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
