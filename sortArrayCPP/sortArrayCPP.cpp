
#include <iostream>
#include <vector>
#include <ctime>
#include <fstream>
#include <thread>
#define DIV 1048576
using namespace std;




void merge(int tablica[], int start, int srodek, int koniec)
{
    int* tab_pom = new int[(koniec + 1 - start)];
    int i = start, j = srodek + 1, k = 0; 
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
    delete[] tab_pom;
}

void merge_sort(int tablica[], int start, int koniec)
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



void printArray(int A[], int size)
{
    for (int i = 0; i < size; i++)
        std::cout << A[i] << " ";
}

// Driver code
#define MAX_THREADS 2
int* arr;
int main()
{


    //int size = 10000000 , 20000000,40000000;
    int size = 10000000;
    long double beginGenerateArray = clock();
    std::vector<std::thread > threadsVec(MAX_THREADS);
    arr = new int[size];
    for (int i = 0; i < size; i++) {
        arr[i] = rand() % size;
    }
    long double endGenerateArray = clock();
    long double finaGenerateArray = (long double)(endGenerateArray - beginGenerateArray) / CLOCKS_PER_SEC;
    std::cout << "Generate Array time " << finaGenerateArray << std::endl;

    long double beginmorgSort = clock();
    int poolSize = size / MAX_THREADS;

    for (int i = 0; i < MAX_THREADS; i++) {
        std::thread tmp(merge_sort,arr,i * poolSize,(i+1)*poolSize-1 );
        threadsVec[i] = std::move(tmp);
    }
    
    for (std::thread& t : threadsVec) {
        t.join();
    }
	merge_sort(arr, 0, size - 1);

    //merge_sort(arr, 0, size - 1);


    long double endmorgSort = clock();
    long double finalSort = (long double)(endmorgSort - beginmorgSort) / CLOCKS_PER_SEC;
    std::cout << "Sort time " << finalSort << std::endl;

    for (int i = 0; i < 100; i++) {std::cout << arr[i] << " " ;}
    delete[] arr;
    return 0;
}
