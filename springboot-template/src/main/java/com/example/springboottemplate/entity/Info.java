package com.example.springboottemplate.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 信息表
 * </p>
 *
 * @author baochengkang
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Info implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 序号
   */
  private Integer id;

  /**
   * 作品
   */
  private String title;

  /**
   * 作者
   */
  private String author;

  /**
   * 阅读数
   */
  private Integer readings;

  /**
   * 时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date date;

}
