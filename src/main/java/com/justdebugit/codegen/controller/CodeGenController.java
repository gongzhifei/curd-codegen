package com.justdebugit.codegen.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.justdebugit.codegen.utils.ResourceUtil;

@RestController
public class CodeGenController {
	
  private static final Logger logger = LoggerFactory.getLogger(CodeGenController.class);
  
  @Autowired
  private CodeGenService codeGenService;
  

  @RequestMapping("sql")
  public void getSqlFiles(MultipartFile file, String packName,HttpServletResponse response) {
    String filePath =  codeGenService.getGenSqlZip(ResourceUtil.fileToString(file), packName);
    String zipName =  StringUtils.substringAfterLast(filePath, "/");
    try {
		
		response.setHeader("Content-Disposition", "attachment; filename=" + zipName );
		response.setHeader("Content-Type","application/zip");
		Files.copy(Paths.get(filePath), response.getOutputStream());
	} catch (IOException e) {
		logger.error(e.getMessage(),e);
	}
  }

  
}
