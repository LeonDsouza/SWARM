/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import org.crce.interns.model.Item;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Leon
 */
public class GenerateCandidateSets {

    public LinkedHashSet<Set<Item>> generateAllPossibilities(Multimap<Item, Item> largeItemset, Map<String, Multimap> ObjectInfo, double minSupport) {
        LinkedHashSet<Item> setOfItems = new LinkedHashSet(1000);
        Set<Item> objectKeys = largeItemset.keySet();
        setOfItems.addAll(objectKeys);
        /**
         * debug
         */
        /*
        for (Item keyprint : objectKeys) {
            System.out.print(keyprint.object + "\t\t" + keyprint.predicate + "\t\t");
            Collection<Item> values = largeItemset.get(keyprint);
            setOfItems.addAll(values);
            for (Item value : values) {
                System.out.print("{" + value.object + "\t\t" + value.predicate + "}");
            }
            System.err.println();
        }
         */
        /**
         * debug
         */
        /*
        System.out.println("Set of items is ");
        for (Item item : setOfItems) {
            System.out.println(item.object + "\t\t" + item.predicate);
        }
         */
        Set<Set<Item>> power = Sets.powerSet(setOfItems);
        /**
         * debug
         */
        //power.forEach(System.out::println);
        /**
         * debug
         */
        /*
            for(Set i1 : power){
                System.out.print(i1.toString());
            }
         */

        LinkedHashSet<Set<Item>> powerLinkedSet = new LinkedHashSet(Collections.synchronizedSet((power)));
        System.gc();
        ArrayList<Set<Item>> removalItems = new ArrayList(100);
        System.out.println(powerLinkedSet.size());
        for (Iterator<Set<Item>> i = powerLinkedSet.iterator(); i.hasNext();) {
            Set<Item> element = i.next();
            if (element.size() <= 2) {
                /**
                 * debug
                 */
                //System.out.println("Yes");
                removalItems.add(element);
            }
        }
        powerLinkedSet.removeAll(removalItems);
        //System.out.println(powerLinkedSet.size());
        removalItems.clear();
        for (Iterator<Set<Item>> i = powerLinkedSet.iterator(); i.hasNext();) {
            Set<Item> element = i.next();
            //System.out.println(element);
            if (getSupport(element, ObjectInfo) < minSupport) {
                removalItems.add(element);
            }
        }
        powerLinkedSet.removeAll(removalItems);
        System.out.println(powerLinkedSet.size());
        /*
        for (Item i : objectKeys) {
            ArrayList<Item> itemListForSupport = new ArrayList(1000);
            itemListForSupport.add(i);
            itemListForSupport.addAll(largeItemset.get(i));
            getSupport(itemListForSupport, ObjectInfo);
            /**
             * debug
         */
        //System.out.println(itemListForSupport.get(0).object + "  " + itemListForSupport.get(0).predicate);
        //itemListForSupport.clear();
        //System.out.println("\n\n");
        /**
         * debug
         */
        /*
            System.out.println("Key item" + itemListForSupport.get(0).object + "\t" + itemListForSupport.get(0).predicate);
            for(Item i1 : itemListForSupport){
                System.out.println(i1.object + "\t\t" + i1.predicate);
            }
            itemListForSupport.clear();
            System.out.println("\n\n\n");
         */
        return powerLinkedSet;
    }

    public double getSupport(Set<Item> listOfItems, Map<String, Multimap> ObjectInfo) {
        double support = 0;
        for (Item element : listOfItems) {
            /**
             * debug
             */
            //System.out.println(element + "\t" + ObjectInfo.get(element.object).get(element.predicate).size());
            support = support + ObjectInfo.get(element.object).get(element.predicate).size();
        }
        return support;
    }

}

//{Marital Status=[Reza, Navid, Nima, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid]}
//{Supervised by=[Reza], Knows=[Reza, Navid, Nima, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid, Reza, Navid]}
