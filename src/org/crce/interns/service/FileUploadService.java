package org.crce.interns.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface FileUploadService {

	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload) throws Exception;
}