/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Leon
 */
public class SWApriori {

    ArrayList<Triple> listOfTriples = new ArrayList(1000);

    public ArrayList<Triple> convertToTriples(String fileName) throws FileNotFoundException, IOException {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                Triple t = new Triple(row[0], row[1], row[2]);
                listOfTriples.add(t);
            }
            /**
             * Debug
             */
            /*
            for (Triple triple : listOfTriples) {
                System.out.println("Subject " + triple.subject);
                System.out.println("\tPredicate " + triple.predicate);
                System.out.println("\t\tObject " + triple.object);
            }*/
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return listOfTriples;
    }

    public ArrayList<Triple> convertToNumericalValues(ArrayList<Triple> listOfTriples, String fileName) throws IOException {
        CSVReader csvReader;
        Set<String> setOfWords = new LinkedHashSet(1000);
        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                for (String row1 : row) {
                    //System.out.println(row1);
                    setOfWords.add(row1);
                }
            }
            //System.out.println("Words in the File are " + setOfWords);
            int count = 0;
            Map<String, Integer> mapOfWords = new LinkedHashMap(1000);
            for (String word : setOfWords) {
                count = count + 1;
                mapOfWords.put(word, count);
            }
            /**
             * debug
             */

            //System.out.println("Map of words is " + mapOfWords);
            /**
             * to want words in the code comment next block out
             */
            /*
            for(Triple t : listOfTriples){
                t.subject = Integer.toString(mapOfWords.get(t.subject));
                t.predicate = Integer.toString(mapOfWords.get(t.predicate));
                t.object = Integer.toString(mapOfWords.get(t.object));
            }
             */
            /**
             * debug
             */
            /*
            for (Triple triple : listOfTriples) {
                System.out.println("Subject " + triple.subject);
                System.out.println("\tPredicate " + triple.predicate);
                System.out.println("\t\tObject " + triple.object);
            }*/
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return listOfTriples;
    }

    public Map<String, Multimap> convertToObjectInfoStructure(ArrayList<Triple> listOfTriples) {
        Map<String, Multimap> ObjectInfo = new LinkedHashMap(1000);
        //Multimap<String, String> multiMap = ArrayListMultimap.create();
        LinkedHashSet<String> objectSet = new LinkedHashSet(1000);
        /**
         * debug
         */
        /*
        for (Triple t : listOfTriples) {
        objectSet.add(t.object);
        }
         */
        listOfTriples.forEach((t) -> {
            objectSet.add(t.object);
        });
        objectSet.forEach((object) -> {
            Multimap<String, String> multiMap = ArrayListMultimap.create();
            listOfTriples.stream().filter((t1) -> (t1.object.equals(object))).forEachOrdered((t1) -> {
                multiMap.put(t1.predicate, t1.subject);
            });
            /**
             * debug
             */
            //System.out.println("Multimap is " + multiMap);
            ObjectInfo.put(object, multiMap);
        });

        /**
         * debug
         */
        //System.out.println("ObjectInfo is " + ObjectInfo);
        /*
        for(String s : objectSet){
            System.out.println(s + " = " + ObjectInfo.get(s));
        }
         */
        return ObjectInfo;
    }
}
