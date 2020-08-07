package com.example.springboottemplate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.example.springboottemplate.service.FileService;
import com.example.springboottemplate.util.Result;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件相关")
@RestController
@RequestMapping(value = "/file")
public class FileController {

  @Autowired
  FileService fileService;

  @ApiOperation(value = "action方式上传文件")
  @RequestMapping(value = "/actionupload", method = RequestMethod.POST)
  public Result actionupload(@RequestParam("file") MultipartFile file) throws IOException {
    Result result = fileService.uploadFile(file);
    return result;
  }

  @ApiOperation(value = "http-request方式上传文件")
  @RequestMapping(value = "/httpupload", produces = "multipart/form-data;charset=utf-8", method = RequestMethod.POST)
  public Result httpupload(HttpServletRequest request) throws IOException, ServletException {
    Part part = request.getPart("file");

    return Result.success();
  }
}