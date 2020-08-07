package com.example.springboottemplate.service;

import java.io.IOException;

import com.example.springboottemplate.util.Result;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件操作 服务类
 * </p>
 *
 * @author baochengkang
 * @since 2020-07-13
 */
public interface FileService {

  Result uploadFile(MultipartFile file) throws IOException;
}