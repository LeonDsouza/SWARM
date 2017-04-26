package org.crce.interns.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.crce.interns.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("certificateUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    //DirectoryPathBean directoryPathBean = new DirectoryPathBean();    
    @Override
    public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload)
            throws Exception {

        //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //get the path for saving the resume
        //String saveDirectory = directoryPathBean.getStudentFolder() + "/" + username + "/Certificates/";
        //String saveDirectory = directoryPathBean.getStudentFolder() + "\\" + username + "\\Certificates\\";	//for windows
        String type = request.getParameter("type");
        final String fullPath = "C:/Users/leons/Desktop/" + fileUpload.getOriginalFilename();;
        int lastDot = fullPath.lastIndexOf('.');
        if (!fileUpload.isEmpty()) {

            

            //File file = new File(fileUpload.getOriginalFilename());
            final String extension = FilenameUtils.getExtension(fullPath);

            // throws IncorrectFileFormatException if the uploaded file is not of the desired extension/type
            

            //throws MaxFileSizeExceededError if the uploaded file exceeds the expected size limit
            final long size = fileUpload.getSize();
            System.out.println(size);
            

            System.out.println(extension);
            String newName = "";
            if (!fileUpload.getOriginalFilename().equals("")) {
                File f1 = new File(fullPath);

                System.out.println(request.getParameter("type"));

                //newName : It is the entire path of the uploaded file with the timestamp of upload appended				
                newName = "C:/Users/leons/Desktop/dump/" + fileUpload.getOriginalFilename();
                System.out.println("new name" + newName);
                File f2 = new File(newName);
                System.out.println("Saving file: " + newName);

                f1.renameTo(f2);
                fileUpload.transferTo(f2);
                //call to the dao 
                //resumeUploadDao.addNewResume(username,newName);
            }

        }
        return fileUpload.getOriginalFilename();
    }
}
