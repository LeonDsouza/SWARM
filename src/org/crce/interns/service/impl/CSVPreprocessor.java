/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Leon
 */
public class CSVPreprocessor {

    String fileName;

    public CSVPreprocessor(String fileName) {
        this.fileName = fileName;
    }

    public void readAndEditCSV(String fileName) {
        CSVReader csvReader;
        List<String[]> listOfRows = new ArrayList(1000);
        try {
            //csvReader = new CSVReader(new FileReader(fileName));
            csvReader = new CSVReader(new FileReader(fileName), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                for (String row1 : row) {
                    // display CSV values

                    //System.out.println("Cell column index: " + i);
                    //System.out.println("Cell Value: " + row[i]);
                    //remove URLs from the file
                    row = removeURLs(row);

                    //System.out.println("-------------");
                }
                //System.out.println("After for " + Arrays.toString(row));

                //check for blank cells or non-triples
                if (!Stream.of(row).anyMatch(x -> x == null || "".equals(x))) {
                    listOfRows.add(row);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        writeCSV(listOfRows);

    }

    public String[] removeURLs(String[] row) {
        String remainingString;
        for (int i = 0; i < row.length; i++) {
            //System.out.println(row[i]);

            //remove URLs
            remainingString = row[i].substring(row[i].indexOf("#") + 1);
            //System.out.println(remainingString);
            row[i] = remainingString;
            remainingString = row[i].substring(row[i].indexOf("/") + 1);
            row[i] = remainingString;
        }
        //System.out.println("Row in removeURL " + Arrays.toString(row));
        return row;
    }

    public void writeCSV(List<String[]> listOfRows) {
        try {
            try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
                //System.out.println("Row in write CSV" + listOfRows);
                writer.writeAll(listOfRows);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVPreprocessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
