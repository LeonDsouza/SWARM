/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.interns.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.crce.interns.service.FileUploadService;
import org.crce.interns.service.impl.StartClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

/**
 *
 * @author Leon
 */
@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/")
    public String loadUploadPage(HttpServletRequest request) {
        System.out.println("Hit /");
        //return "Test1";
        return "Test1";
    }

    @RequestMapping(value = "/uploadCertificate", method = RequestMethod.POST)
    public ModelAndView certificateUpload(@RequestParam CommonsMultipartFile file, HttpSession session, Model model)
            throws Exception {
        String path = session.getServletContext().getRealPath("/");
        String filename = file.getOriginalFilename();
        
        System.out.println(path + " " + filename);
        try {
            byte barr[] = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path + "/" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //return new ModelAndView("table", "filename", path + "/" + filename);
        String s = path + "/" + filename;
        StartClass start = new StartClass();
        start.main(s);
        String ex = "1.csv";
        String output = s.concat(ex);
        System.out.println("out" + output);
        model.addAttribute("output",output);
        //return new ModelAndView("table", "output", path + "/" + filename);
        return new ModelAndView("table", "output", output);
    }

}
