import random
import time
import tracemalloc
import os
from multiprocessing import Pool
# Code to print the list
def merge(tablica,start,srodek,koniec):
    tab_pom=[None]*(koniec+1-start)
    i=start
    j=srodek+1
    k=0
    while i<=srodek and j<=koniec:
        if tablica[j]<tablica[i]:
            tab_pom[k]=tablica[j]
            j+=1
        else:
            tab_pom[k]=tablica[i]
            i+=1
        k+=1
    if i<=srodek:
        while i<=srodek:
            tab_pom[k]=tablica[i]
            i+=1
            k+=1
    else:
        while j<=koniec:
            tab_pom[k]=tablica[j]
            j+=1
            k+=1
    for i in range(0,(koniec-start+1)):
        tablica[start+i]=tab_pom[i]
def merge_sort(tablica,start,koniec):

    #print('process id:', os.getpid())
    if start!=koniec:
        srodek=int((start+koniec)/2)
        merge_sort(tablica,start,srodek)
        merge_sort(tablica,srodek+1,koniec)
        merge(tablica,start,srodek,koniec)
    return tablica

                # Driver Code
MAX_THREADS=6
if __name__ == '__main__':
    arr = []
    print("THREADS: ",MAX_THREADS)
    #siezeArray=[10000000,20000000,40000000]
    siezeArray=[10000000]
    for size in siezeArray:
        poolSize = size / MAX_THREADS;
        #size=100
        tracemalloc.start()
        time1=time.time()
        for i in range(0,size):
            tmp=random.randint(0,size)
            arr.append(tmp)
        time2=time.time()
        tmptime=time2-time1
        print("SIZE: ",size)
        print("Making list time: ",tmptime)
        threadTab = [None] * MAX_THREADS
        arrayspools=[]
        amountofpoolsStart = []
        amountofpoolsEnd = []
        amountofpoolsMid=[]
        arrayAll=[]
        numbeorThread = []
        timeSortStart = time.time()
        for i in range(0, MAX_THREADS):
            #start = i*poolsize, koniec= (i+1)*poolsize
            #amountofpoolsX.append([int(i * poolSize), int((i + 1) * poolSize)])
            #amountofpoolsStart.append(int(i*poolSize))
            #amountofpoolsEnd.append(int((i + 1) * poolSize))
            #amountofpoolsMid.append(int(int(i*poolSize)/int((i + 1) * poolSize)))
            #arrayspools.append(arr[int(i*poolSize):int((i + 1) * poolSize)])
            #arrayAll.append([arr[int(i*poolSize):int((i + 1) * poolSize)],int(i*poolSize),int((i + 1) * poolSize)])
            arrayAll.append([arr[int(i*poolSize):int((i + 1) * poolSize)],0,int(poolSize)-1])
        p = Pool(processes=MAX_THREADS)
        result = p.starmap(merge_sort,(arrayAll))
        p.close()
        p.join()
        dupa=[]
        for i in result:
            dupa+=i

        #print("Result: ",dupa)
        arr=merge_sort(dupa,0,size-1)



        #merge_sort(arr,0,size-1)

        timeSortStop=time.time()
        tmptime2=timeSortStop-timeSortStart
        print("Time of sorting list: ",tmptime2)
        current, peak = tracemalloc.get_traced_memory()
        print(f"Current memory usage is {current / 10 ** 6}MB; Peak was {peak / 10 ** 6}MB")
        #print(arr)
        tracemalloc.stop()

