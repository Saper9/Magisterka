package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        long startMerge = System.currentTimeMillis();
        System.gc();
        Runtime rt = Runtime.getRuntime();
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("bazaBIG.json");
        JSONArray item = (JSONArray) parser.parse(reader);



        long finishMerge = System.currentTimeMillis();
        long timeElapsedMerge = finishMerge - startMerge;
        System.out.println("Loading time: "+timeElapsedMerge);
/*
        long start1 = System.currentTimeMillis();
        int iter1=0;
        for(int i=0;i<item.size();i++){
            JSONObject tmp= (JSONObject) item.get(i);
            if(tmp.containsKey("transcription")){
                String trn= (String) tmp.get("transcription");
                if(trn.contains("Caesar")){
                    iter1++;
                }
            }

        }
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - start1;
        System.out.println("Search 1 time: "+timeElapsed1);



        long start2 = System.currentTimeMillis();
        int iter2=0;
        for(int i=0;i<item.size();i++) {
            JSONObject tmp = (JSONObject) item.get(i);
            if (tmp.containsKey("people")) {
                JSONArray ppl = (JSONArray) tmp.get("people");
                for (int j = 0; j < ppl.size(); j++) {
                    JSONObject tmpppl = (JSONObject) item.get(j);
                    if (tmpppl.containsKey("gender")) {
                        String trn2 = (String) tmpppl.get("gender");
                        if (trn2 == "female") {
                            iter2++;
                        }
                    }

                }

            }
        }
        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        System.out.println("Search 2 time: "+timeElapsed2);


        long start3 = System.currentTimeMillis();
        int iter3=0;
        for(int i=0;i<item.size();i++) {
            JSONObject tmp = (JSONObject) item.get(i);
            if (tmp.containsKey("material")) {
                JSONObject tmp3= (JSONObject) item.get(i);
                String material= (String) tmp3.get("material");
                if(material.contains("marble")){
                    if(tmp3.containsKey("not_before")){
                        int year=Integer.parseInt((String) tmp3.get("not_before"));
                        if(year<=200){
                            iter3++;
                        }
                    }
                }

            }
        }
        long finish3 = System.currentTimeMillis();
        long timeElapsed3 = finish3 - start3;
        System.out.println("Search 3 time: "+timeElapsed3);



        long start4 = System.currentTimeMillis();
        int iter4=0;
        for(int i=0;i<item.size();i++) {
            JSONObject tmp = (JSONObject) item.get(i);
            if (tmp.containsKey("language")) {
                if(tmp.get("language")=="Greek"){
                   if(tmp.containsKey("country")){
                       if(tmp.get("country")=="Italy"){
                           iter4++;
                       }
                   }
                }

            }
        }
        long finish4 = System.currentTimeMillis();
        long timeElapsed4 = finish4 - start4;
        System.out.println("Search 4 time: "+timeElapsed4);



        long start5 = System.currentTimeMillis();
        int iter5=0;
        for(int i=0;i<item.size();i++) {
            JSONObject tmp = (JSONObject) item.get(i);
            if (tmp.containsKey("findspot")) {
                if(tmp.get("findspot")=="Roma"){
                    if(tmp.containsKey("transcription")){
                        String trn= (String) tmp.get("transcription");
                        if(trn.contains("Caesar")){
                            if(tmp.containsKey("not_before")){
                                int year=Integer.parseInt((String) tmp.get("not_before"));
                                if(year<=200){
                                    iter5++;
                                }
                            }
                        }
                    }

                }

            }
        }
        long finish5 = System.currentTimeMillis();
        long timeElapsed5 = finish5 - start5;
        System.out.println("Search 5 time: "+timeElapsed5);
*/
        long start6 = System.currentTimeMillis();
        int iter6=0;
        for(int i=0;i<item.size();i++) {
            JSONObject tmp = (JSONObject) item.get(i);
            if (tmp.containsKey("language")) {
                if(tmp.get("language")=="Greek"){
                    String material= (String) tmp.get("material");
                    if(material.contains("marble")){
                        if (tmp.containsKey("people")) {
                            JSONArray ppl = (JSONArray) tmp.get("people");
                            for (int j = 0; j < ppl.size(); j++) {
                                JSONObject tmpppl = (JSONObject) item.get(j);
                                if (tmpppl.containsKey("gender")) {
                                    String trn2 = (String) tmpppl.get("gender");
                                    if (trn2 == "female") {
                                        iter6++;
                                    }
                                }

                            }

                        }
                    }
                }

            }
        }
        long finish6 = System.currentTimeMillis();
        long timeElapsed6 = finish6 - start6;
        System.out.println("Search 6 time: "+timeElapsed6);






        long usedMB = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
        System.out.println("Memory  MB usage: "+usedMB);

    }
}
