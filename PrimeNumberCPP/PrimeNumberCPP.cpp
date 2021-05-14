#include <iostream>
#include <vector>
#include <ctime>
#include <thread> 
#define MAX 500000
#define MAX_THREADS 6
std::vector<std::vector<int>> primeNumbers(MAX_THREADS);

bool isPrime(int n)
{
    // Corner case
    if (n <= 1)
        return false;

    // Check from 2 to n-1
    for (int i = 2; i < n; i++)
        if (n % i == 0)
            return false;

    return true;
}
void isPrimeMultiThreading(int start, int end,int threadNumber)
{
    std::vector<int>primeNumbers2;
    for (int iter = start; iter < end; iter++)
    {
        if (isPrime(iter)) 
        {
            primeNumbers2.push_back(iter);
        }
    }
    primeNumbers[threadNumber]=primeNumbers2;
}
void printPrime() {
   
    int iter = 0;
    for (int i = 0; i < MAX_THREADS; i++) {

        for (int j = 0; j < primeNumbers[i].size(); j++)
        {
            iter++;
            //std::cout << primeNumbers[i][j] << " ";
        }
    }
    std::cout << "Prime numbers amount " << iter << std::endl;
}
    int main()
    {
        long double beginSearchingPrime = clock();
        int poolSize = MAX / MAX_THREADS;
        std::vector<std::thread > threadsVec(MAX_THREADS);
        
     

        
        for (int i = 0; i < MAX_THREADS; i++) 
        {
           // ThreadVector.emplace_back([&]() {function(a, b, Obj, Obj2); }); // Pass by reference here, make sure the object lifetime is correct
            std::thread tmp(isPrimeMultiThreading,i * poolSize, (i + 1) * poolSize,i);
            threadsVec[i]=std::move(tmp);
            //std::cout << "start: " << i * poolSize << " stop: " << (i + 1) * poolSize << std::endl;

        }
        for (std::thread& t : threadsVec) {
            t.join();
        }
        /*
        for (int iter = 1; iter < MAX; iter++)
        {
            if (isPrime(iter)) {
                primeNumbers.push_back(iter);
            }
        }
        */
        long double endSearchingPrime = clock();
        long double finalSearchingPrime = (long double)(endSearchingPrime - beginSearchingPrime) / CLOCKS_PER_SEC;
        std::cout << "Search prime time " << finalSearchingPrime << std::endl;
        std::cout << "Prime numbers in " << MAX << " range " << std::endl;
        //printPrime();
        
        return 0;
    }
