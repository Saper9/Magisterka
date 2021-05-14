#include <iostream>
#include "nlohmann/json.hpp"
#include <fstream>
#include <ctime>
using json = nlohmann::json;
int main()
{
    json baza1;
    long double begin = clock();
    std::ifstream file1("bazaBIG.json");
    file1 >> baza1;
    file1.close();
    long double end = clock();
    long double finaltime = (long double)(end - begin) / CLOCKS_PER_SEC;
    std::cout << "Czas wczytywania bazy " << finaltime << std::endl;
    int iter1 = 0;
    long double begin1 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("transcription")) {
            std::string trn = baza1[i]["transcription"];
            //std::cout << trn << std::endl;

            if (trn.find("Caesar") != std::string::npos) {
                iter1++;
            }
            trn.clear();
        }
        
    }
    
    long double end1 = clock();
    long double final1 = (long double)(end1 - begin1) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 1 " << final1 << std::endl;


    int iter2 = 0;
    long double begin2 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("people")) {
            auto trn = baza1[i]["people"];
            for (int j = 0; j < trn.size(); j++) {
                if (trn[j].contains("gender")) {
                    if (trn[j]["gender"] == "female") {
                        iter2++;
                    }
                }
            }
            trn.clear();
            
            
        }

    }
    long double end2 = clock();
    long double final2 = (long double)(end2 - begin2) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 2 " << final2 << std::endl;


    int iter3 = 0;
    long double begin3 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("material")) {
            std::string trn = baza1[i]["material"];
            if (trn.find("marble") != std::string::npos) {
                if (baza1[i].contains("not_before")) {
                    if (baza1[i]["not_before"] <= 200)
                    {
                        iter3++;;
                    }
                }
            }
            trn.clear();
            
        }
    }
    long double end3 = clock();
    long double final3 = (long double)(end3 - begin3) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 3 " << final3 << std::endl;

    int iter4 = 0;
    long double begin4 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("language")) {
            if (baza1[i].contains("country")) {
                std::string country = baza1[i]["country"];
                std::string language = baza1[i]["language"];

                if (country == "Italy" and language == "Greek") {
                    iter4++;
                }
                
                country.clear();
                language.clear();

            }
        }
    }
    long double end4 = clock();
    long double final4 = (long double)(end4 - begin4) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 4 " << final4 << std::endl;

    int iter5 = 0;
    long double begin5 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("findspot")) {
            std::string findspot = baza1[i]["findspot"];
            if (findspot == "Roma") {
                if (baza1[i].contains("transcription")) {
                    std::string trn = baza1[i]["transcription"];


                    if (trn.find("Caesar") != std::string::npos) {
                        if (baza1[i].contains("not_before")) {
                            if (baza1[i]["not_before"] <= 200)
                            {
                                iter5++;;
                            }
                        }
                    }
                    trn.clear();
                }
            }
            findspot.clear();

           
        }
    }
    long double end5 = clock();
    long double final5 = (long double)(end5 - begin5) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 5 " << final5 << std::endl;


    int iter6 = 0;
    long double begin6 = clock();
    for (int i = 0; i < baza1.size(); i++) {
        if (baza1[i].contains("language")) {
                std::string language = baza1[i]["language"];
                if (language == "Greek") {
                    if (baza1[i].contains("material")) {
                        std::string trn = baza1[i]["material"];
                        if (trn.find("marble") != std::string::npos) {
                            if (baza1[i].contains("people")) {
                                auto trn = baza1[i]["people"];
                                for (int j = 0; j < trn.size(); j++) {
                                    if (trn[j].contains("gender")) {
                                        if (trn[j]["gender"] == "female") {
                                            iter6++;
                                        }
                                    }
                                }
                            }
                        }


                    }
                    
                }
               

            }
        
        
    }
    long double end6 = clock();
    long double final6 = (long double)(end6 - begin6) / CLOCKS_PER_SEC;
    std::cout << "czas wyszukiwania 6 " << final6 << std::endl;

}

