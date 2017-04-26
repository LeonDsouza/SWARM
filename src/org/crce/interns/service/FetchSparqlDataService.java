/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.service;
import java.io.IOException;
/**
 *
 * @author Leon
 */
public interface FetchSparqlDataService {
    public String getDataFromEndpoint(String query, String endpoint, String format) throws IOException;
    public boolean serviceStateCheck(String endpoint);
}
