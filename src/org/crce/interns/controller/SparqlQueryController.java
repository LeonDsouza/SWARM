/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.crce.interns.service.FetchSparqlDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Leon
 */
@Controller
public class SparqlQueryController {
    
    @Autowired
    private FetchSparqlDataService fetchSparqlDataService;
    
    
    @RequestMapping("/sparql")
    public String loadForm(){
        return "FetchData";
    }
    
    
     @RequestMapping(value = "/hitSparql", method = RequestMethod.POST)
    public String getFormData(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println(request.getParameter("query"));
        System.out.println(request.getParameter("endpoint"));
        System.out.println(request.getParameter("format"));
        String query = request.getParameter("query");
        String endpoint = request.getParameter("endpoint");
        String format = request.getParameter("format");
        String outputOfQuery = fetchSparqlDataService.getDataFromEndpoint(query, endpoint, format);
        System.out.println(outputOfQuery);
        return "FetchData";
    }
}
