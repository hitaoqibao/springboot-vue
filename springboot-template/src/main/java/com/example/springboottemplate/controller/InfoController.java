package com.example.springboottemplate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.alibaba.fastjson.JSONObject;
import com.example.springboottemplate.service.InfoService;
import com.example.springboottemplate.util.Result;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 信息表 前端控制器
 * </p>
 *
 * @author baochengkang
 * @since 2020-08-10
 */
@Api(tags = "信息表相关")
@RestController
@RequestMapping(value = "/info")
public class InfoController {

  @Autowired
  InfoService infoService;

  @ApiOperation(value = "信息表所有信息")
  @RequestMapping(value = "/allinfo", method = RequestMethod.GET)
  public Result allinfo() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list", infoService.list());
    return Result.success(jsonObject);
  }

}
