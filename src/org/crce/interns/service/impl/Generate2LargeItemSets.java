/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import org.crce.interns.model.Item;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Leon
 */
public class Generate2LargeItemSets {

    public Multimap<Item, Item> generate2LargeItemsets(Map<String, Multimap> ObjectInfo, double minSupport, ArrayList<Triple> listOfTriples) {
        LinkedHashSet<String> objectSet = new LinkedHashSet(1000);
        int yesCount = 0;
        Multimap<Item, Item> largeItemSet = ArrayListMultimap.create();
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
        for (String i : objectSet) {
            for (String j : objectSet) {
                /**
                 * debug
                 */
                //System.out.println(i + "\t\t" + j);
                if (!i.equals(j)) {
                    /**
                     * debug
                     */
                    //System.out.println("-----------------------------");
                    Set<String> setOfPredicatesI = ObjectInfo.get(i).keySet();
                    /**
                     * debug
                     */
                    //System.out.println("\tSet of predicates " + i + "\t" + setOfPredicatesI);
                    Set<String> setOfPredicatesJ = ObjectInfo.get(j).keySet();
                    /**
                     * debug
                     */
                    //System.out.println("\tSet of predicates " + j + "\t" + setOfPredicatesJ);
                    for (String i1 : setOfPredicatesI) {
                        for (String j1 : setOfPredicatesJ) {
                            Collection<String> subjectI = ObjectInfo.get(i).get(i1);
                            /**
                             * debug
                             */
                            //System.out.println("\t\tSubject list of " + i1 + "\t" + subjectI);
                            Collection<String> subjectJ = ObjectInfo.get(j).get(j1);
                            /**
                             * debug
                             */
                            //System.out.println("\t\tSubject list of " + j1 + "\t" + subjectJ);
                            //double count = 0;
                            /**
                             * debug
                             */
                            //System.out.println("Intesection count for" + subjectI + " " + subjectJ + " = " + count);
                            double count = intersectionCount(subjectI, subjectJ);
                            /**
                             * debug
                             */
                            //System.out.println("Intesection count = " + count);

                            if (count >= minSupport) {
                                yesCount = yesCount + 1;
                                /**
                                 * debug
                                 */
                                //System.out.println("Yes is greater " + yesCount);
                                Item item1 = new Item(i, i1);
                                Item item2 = new Item(j, j1);
                                /**
                                 * debug
                                 */
                                //largeItemSet.add(item1);
                                //largeItemSet.add(item2);
                                if (largeItemSet.size() <= 28) {
                                    largeItemSet.put(item1, item2);
                                }
                            }
                        }
                    }
                    /**
                     * debug
                     */
                    //System.out.println("----------------");
                }
            }
        }
        /**
         * debug
         */

        //System.out.println("Object1" + "\t\tPredicate1\t\t" + "Object2\t\t" + "Predicate2");
        Set<Item> keys = largeItemSet.keySet();
        /*
        for (Item keyprint : keys) {
            System.out.print(keyprint.object + "\t\t" + keyprint.predicate + "\t\t");
            Collection<Item> values = largeItemSet.get(keyprint);
            for (Item value : values) {
                System.out.print("{" + value.object + "\t\t" + value.predicate + "}");
            }
            System.err.println();
        }
         */
        System.out.println(keys.size());

        return largeItemSet;
    }

    public double intersectionCount(Collection<String> subjectI, Collection<String> subjectJ) {
        double count = 0;
        for (String subject1 : subjectI) {
            for (String subject2 : subjectJ) {
                if (subject1.equals(subject2)) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

}
