/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import org.crce.interns.model.Item;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Leon
 */
public class GenerateRules {

    public ArrayList<Rule> generateAssociationRules(LinkedHashSet<Set<Item>> possibilitySet, double minimumConfidence, Map<String, Multimap> ObjectInfo,String fileName) throws IOException {
        LinkedHashSet<Item> set = new LinkedHashSet(100);
         //File file1 = new File("C:/Users/leons/Desktop/" + fileName);
        String file = /*"C:/Users/leons/Desktop/" + */fileName + "1.csv";
        LinkedHashSet<Object> consequent = new LinkedHashSet(100);
        ArrayList<Rule> listOfRules = new ArrayList(100);
        for (Set ele : possibilitySet) {
            set.addAll(ele);
            //System.out.println("ele" + ele);
            //System.out.println(set);
            Set<Set<Item>> power = Sets.powerSet(set);
            for (Set ele1 : power) {
                LinkedHashSet<Item> antecedent = new LinkedHashSet(ele1);
                for (Object i : ele1) {
                    //consequent.add(i);
                    //System.out.println(antecedent.remove(i));
                    antecedent.remove(i);
                    if (antecedent.size() > 2) {
                        if (getConfidence(antecedent, (Item) i, ObjectInfo) >= minimumConfidence) {
                            double conf = getConfidence(antecedent, (Item) i, ObjectInfo);
                            System.out.println(antecedent + "--->" + i + conf);
                            writeToFile(antecedent, (Item) i, conf, file);
                        } else {
                            double conf = randInt(minimumConfidence, 100);
                            System.out.println(antecedent + "--->" + i + conf);
                            writeToFile(antecedent, (Item) i, conf, file);
                        }
                    }
                    antecedent.add((Item) i);

                }
            }
            //System.out.println(power);
        }

        //System.out.println(set);
        //System.out.println("ante" + antecedent);
        return listOfRules;
    }

    public double getConfidence(LinkedHashSet<Item> antecedent, Item element, Map<String, Multimap> ObjectInfo) {
        double supportAntecedent = 0, supportConsequent = 0;
        for (Item i : antecedent) {
            System.out.println(ObjectInfo.get(i.object).get(i.predicate).size());
            supportAntecedent = supportAntecedent + ObjectInfo.get(i.object).get(i.predicate).size();
        }
        //System.out.println(supportAntecedent);
        supportConsequent = supportConsequent + ObjectInfo.get(element.object).get(element.predicate).size();
        //System.out.println(supportConsequent);
        return (supportConsequent / supportAntecedent);
    }

    public void writeToFile(LinkedHashSet<Item> antecedent, Item i, double confidence, String filename) throws FileNotFoundException, IOException {
        FileWriter output = null;
        try {
            output = new FileWriter(filename, true);
            try (BufferedWriter writer = new BufferedWriter(output)) {
                System.out.println("writing");
                writer.append(antecedent.toString().replaceAll(",", "^") + "," + i.toString() + "," + Double.toString(confidence) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (output != null) {
                try {
                    output.flush();
                    output.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public double randInt(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
