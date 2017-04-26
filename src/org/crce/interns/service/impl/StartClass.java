/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import org.crce.interns.model.Item;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Leon
 */
public class StartClass {

    public void main(String filename) throws IOException {
        //String fileName = "C:/Users/leons/Desktop/dump";
        //String fileName = "C:/Users/leons/Desktop/" + filename;
        double minSupport = 40;
        double minimumConfidence = 65;
        CSVPreprocessor preprocessor = new CSVPreprocessor(filename);
        preprocessor.readAndEditCSV(filename);
        System.out.println("pre done");
        SWApriori swapriori = new SWApriori();
        ArrayList<Triple> listOfTriples = swapriori.convertToTriples(filename);
        System.out.println("trip done");
        ArrayList<Triple> numericalTriples = swapriori.convertToNumericalValues(listOfTriples, filename);
        System.gc();
        Map<String, Multimap> ObjectInfo = swapriori.convertToObjectInfoStructure(numericalTriples);
        System.gc();
        System.out.println("objectinfo done");
        Generate2LargeItemSets generate = new Generate2LargeItemSets();
        System.gc();
        
        Multimap<Item, Item> largeItemset = generate.generate2LargeItemsets(ObjectInfo, minSupport, listOfTriples);
        System.gc();
        System.out.println("2LIS done");
        /**
         * debug
         */
        /*
        System.out.println("Before");
        System.out.println("Processors" + Runtime.getRuntime().availableProcessors());
        System.out.println("Free memory" + Runtime.getRuntime().freeMemory());
        System.out.println("Max memory" + Runtime.getRuntime().maxMemory());
        System.out.println("Total memory" + Runtime.getRuntime().totalMemory());
         */
 /*
        System.out.println("Object" + "\t\tPredicate");
        System.out.println(largeItemset.size());
        for(Item i : largeItemset){
            System.out.println(i.object + "\t\t" + i.predicate);
        }*/
        GenerateCandidateSets candidate = new GenerateCandidateSets();
        LinkedHashSet<Set<Item>> possibilitySet = candidate.generateAllPossibilities(largeItemset, ObjectInfo, minSupport);
        System.gc();
        System.out.println("Cands done");
        /*
        for(Set ele : possibilitySet){
            System.out.println(ele);
        }
         */
        GenerateRules generateRules = new GenerateRules();
        ArrayList<Rule> listOfRules = generateRules.generateAssociationRules(possibilitySet, minimumConfidence, ObjectInfo, filename);
        System.out.println("rules done");
        /**
         * debug
         */
        /*
        System.out.println("After");
        System.out.println("Free memory" + Runtime.getRuntime().freeMemory());
        System.out.println("Max memory" + Runtime.getRuntime().maxMemory());
        System.out.println("Total memory" + Runtime.getRuntime().totalMemory());
         */
    }
}
