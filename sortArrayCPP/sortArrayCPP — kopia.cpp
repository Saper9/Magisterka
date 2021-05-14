
#include <iostream>
#include <vector>
#include <ctime>
#include <iostream>
#include <fstream>

#include <omp.h>
// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..r]
#define DIV 1048576
void merge(int arr[], int l, int m, int r)
{
    int n1 = m - l + 1;
    int n2 = r - m;

    // Create temp arrays
    //int L[n1], R[n2];
    int *L = new int[n1];
    int* R = new int[n2];


    // Copy data to temp arrays L[] and R[]
    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    // Merge the temp arrays back into arr[l..r]

    // Initial index of first subarray
    int i = 0;

    // Initial index of second subarray
    int j = 0;

    // Initial index of merged subarray
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

    // Copy the remaining elements of
    // L[], if there are any
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of
    // R[], if there are any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }

    delete[] L;
    delete[] R;
}

// l is for left index and r is
// right index of the sub-array
// of arr to be sorted */
void mergeSort(int arr[], int l, int r) {
    if (l >= r) {
        return;//returns recursively
    }
    int m = l + (r - l) / 2;
    //std::cout << "Thread nr: " << omp_get_thread_num() << std::endl;

 
#pragma omp task shared(arr) 
    {
        mergeSort(arr, l, m);
    }
    

#pragma omp task shared(arr)
    {
        mergeSort(arr, m + 1, r);
    }
    

#pragma omp taskwait
    {
        merge(arr, l, m, r);
    }
//mergeSort(arr, l, m);
//mergeSort(arr, m + 1, r);  
//merge(arr, l, m, r);
}

// UTILITY FUNCTIONS
// Function to print an array
void printArray(int A[], int size)
{
    for (int i = 0; i < size; i++)
        std::cout << A[i] << " ";
}

// Driver code
int main()
{
    omp_set_dynamic(0);
    omp_set_nested(1);
    //omp_set_num_threads(16);
    int size = 10000000;
    //int arr[] = { 12, 11, 13, 5, 6, 7 };
    long double beginGenerateArray = clock();
    int* arr = new int[size];
    for (int i = 0; i < size; i++) {
        arr[i] = rand() % size;
    }
    long double endGenerateArray = clock();
    long double finaGenerateArray = (long double)(endGenerateArray - beginGenerateArray) / CLOCKS_PER_SEC;
    std::cout << "Generate Array time " << finaGenerateArray << std::endl;

   
    long double beginmorgSort = clock();

       
#pragma omp parallel
        {
            #pragma omp single
            {
                mergeSort(arr, 0, size - 1);
            }
        
        }


    long double endmorgSort = clock();
    long double finalSort= (long double)(endmorgSort - beginmorgSort) / CLOCKS_PER_SEC;
    std::cout << "Sort time " << finalSort << std::endl;
    
    for (int i = 0; i < 10; i++) {
        std::cout << arr[i] << std::endl;
    }
    delete [] arr;
    return 0;
}
