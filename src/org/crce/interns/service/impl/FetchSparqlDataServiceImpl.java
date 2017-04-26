/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryExceptionHTTP;
import org.crce.interns.service.FetchSparqlDataService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leon
 */
@Service("fetchSparqlDataService")
public class FetchSparqlDataServiceImpl implements FetchSparqlDataService {

    @Override
    public String getDataFromEndpoint(String query, String endpoint, String format) throws IOException{
        if (serviceStateCheck(endpoint)) {
            ParameterizedSparqlString qs = new ParameterizedSparqlString(query);
            QueryExecution exec = QueryExecutionFactory.sparqlService(endpoint, qs.asQuery());
            ResultSet results = exec.execSelect();
            String file = "C:/Users/leons/Desktop/SparqlQuery";
            FileOutputStream fosCSV = new FileOutputStream(file + ".csv");
            FileOutputStream fosTSV = new FileOutputStream(file + ".tsv");
            FileOutputStream fosXML = new FileOutputStream(file + ".xml");
            FileOutputStream fosJSON = new FileOutputStream(file + ".json");
            if (format.equals("CSV")) {
                try {
                    ResultSetFormatter.outputAsCSV(fosCSV, results);
                    fosCSV.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FetchSparqlDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (format.equals("TSV")) {
                try {
                    ResultSetFormatter.outputAsTSV(fosTSV, results);
                    fosTSV.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FetchSparqlDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (format.equals("XML")) {
                try {
                    ResultSetFormatter.outputAsXML(fosXML, results);
                    fosXML.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FetchSparqlDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (format.equals("JSON")) {
                try {
                    ResultSetFormatter.outputAsJSON(fosJSON, results);
                    fosJSON.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FetchSparqlDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return "Check Output file as C:/Users/leons/Desktop/SparqlQuery."+format;
        }
        else
            return "Error Encountered!";
    }

    @Override
    public boolean serviceStateCheck(String endpoint) {
        String service = "http://dbpedia.org/sparql";  //give name of sparql endpoint you want to check here
        String query = "ASK{ }";  //ask query
        boolean isRunning = false;
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        qe.setTimeout(1000000000);  //increase timeout! Slow Internet Problems for me!
        try {
            //check if service works
            if (qe.execAsk()) {
                System.out.println(service + " is  UP");
                isRunning = true;
            }
        } catch (QueryExceptionHTTP e) {
            System.out.println(service + " is down");
        } finally {
            qe.close();
        }
        return isRunning;
    }

}
