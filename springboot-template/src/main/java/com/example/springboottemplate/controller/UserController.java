package com.example.springboottemplate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.example.springboottemplate.service.UserService;
import com.example.springboottemplate.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author baochengkang
 * @since 2020-07-13
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired
  UserService userservice;

  /**
   * 登录
   */
  @ApiOperation(value = "用户登录")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Result login(String username, String password) {
    Result result = userservice.getLogin(username, password);
    return result;
  }

  /**
   * 已登录，token验证
   */
  @ApiOperation(value = "已登录，token验证")
  @RequestMapping(value = "/info", method = RequestMethod.POST)
  public Result info() {
    Result result = userservice.getInfo();
    return result;
  }

  /**
   * 退出登录
   */
  @ApiOperation(value = "退出登录")
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public Result logout() {
    Result result = userservice.getlogout();
    return result;
  }

  /**
   * 注册时用户名验证
   */
  @ApiOperation(value = "注册时用户名验证")
  @RequestMapping(value = "/checkname", method = RequestMethod.POST)
  public Result checkname(String username) {
    if (StringUtils.isEmpty(username)) {
      return Result.success();
    }
    return Result.success(userservice.checkUsername(username) ? null : "用户已存在");
  }

  /**
   * 注册
   */
  @ApiOperation(value = "注册")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public Result register(String username, String password) {
    Result result = userservice.registerUser(username, password);
    return result;
  }

  /**
   * 测试
   */
  @RequestMapping(value = "/getusertable", method = RequestMethod.POST)
  public Result getusertable(int page, int limit, String value) {
    Result result = userservice.getUserTableSe(page, limit, value);
    return result;
  }
}
