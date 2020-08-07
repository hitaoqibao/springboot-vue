package com.example.springboottemplate.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.springboottemplate.service.FileService;
import com.example.springboottemplate.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

  // 将 yml 中的自定义配置注入到这里
  @Value("${file.path}")
  private String filePath;

  // 设置固定的日期格式
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  // spring-jdbc
  @Autowired
  private JdbcTemplate jdbc;

  @Override
  public Result uploadFile(MultipartFile file) throws IOException {
    // TODO Auto-generated method stub
    // 常规的存储到文件夹
    File filepath = new File(filePath);
    // 目录不存在，则创建
    if (!filepath.exists()) {
      filepath.mkdirs();
    }
    // 得到格式化后的日期
    String format = sdf.format(new Date());
    // 获取上传文件的名称
    String fileName = file.getOriginalFilename();
    // 时间和文件名拼接
    String newFileName = format + "_" + fileName;
    // 文件保存位置及新文件名
    File dest = new File(filepath + "\\" + newFileName);
    // 保存文件
    try {
      file.transferTo(dest);
      return Result.success("文件上传成功");
    } catch (IllegalStateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return Result.success("文件上传失败");

    // // 以二进制方式存储到数据库中
    // String fileName = file.getOriginalFilename();
    // long size = file.getSize();
    // String type = fileName.substring(fileName.lastIndexOf(".") + 1);
    // // 读取文件二进制存储到数据库,mysql字段类型longblob
    // byte[] buffer = file.getBytes();
    // String uuid = UUID.randomUUID().toString().replace("_", "");
    // String sql = "insert into files(id,name,size,type,data) values(?,?,?,?,?)";
    // final LobHandler lobHandler = new DefaultLobHandler();
    // jdbc.execute(sql, new
    // AbstractLobCreatingPreparedStatementCallback(lobHandler) {
    // @Override
    // protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws
    // SQLException, DataAccessException {
    // ps.setString(1, uuid);
    // ps.setString(2, fileName);
    // ps.setDouble(3, size);
    // ps.setString(4, type);
    // lobCreator.setBlobAsBytes(ps, 5, buffer);
    // }
    // });
    // return Result.success("文件上传成功");
  }

}