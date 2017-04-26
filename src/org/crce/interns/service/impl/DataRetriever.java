/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import jena.query;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *
 * @author Leon
 */
public class DataRetriever {

    //public static void main(String[] args) 
    public void getAllData(String fileName) {
        String filename = "C:/Users/leons/Desktop/this/" + fileName;
        Model model = ModelFactory.createDefaultModel();
        OntModel model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);

        try {
            File file = new File(filename);
            FileInputStream reader = new FileInputStream(file);
            model.read(reader, null);
            File file1 = new File("C:/Users/leons/Desktop/result.csv");
            String query1 = "SELECT ?s ?p ?o where {?s ?p ?o} ";

            Query query = QueryFactory.create(query1);
            QueryExecution exe = QueryExecutionFactory.create(query, model1);

            ResultSet RES = exe.execSelect();

            ResultSetFormatter.outputAsCSV(new FileOutputStream(file1), RES);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
