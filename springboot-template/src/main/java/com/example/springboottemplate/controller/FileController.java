package com.example.springboottemplate.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.springboottemplate.service.FileService;
import com.example.springboottemplate.util.NonStaticResourceHttpRequestHandler;
import com.example.springboottemplate.util.Result;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "文件相关")
@RestController
@RequestMapping(value = "/file")
@AllArgsConstructor
public class FileController {

  @Autowired
  FileService fileService;

  private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

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

  @GetMapping(value = "/video")
  public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

    // 假如我把视频1.mp4放在了static下的video文件夹里面
    // sourcePath 是获取resources文件夹的绝对地址
    // realPath 即是视频所在的磁盘地址
    String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
    String realPath = sourcePath + "static/video/1.mp4";

    Path filePath = Paths.get(realPath);
    if (Files.exists(filePath)) {
      String mimeType = Files.probeContentType(filePath);
      if (!StringUtils.isEmpty(mimeType)) {
        response.setContentType(mimeType);
      }
      request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
      nonStaticResourceHttpRequestHandler.handleRequest(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
    }
  }
}