package com.example.springboottemplate.service.impl;

import com.example.springboottemplate.entity.Info;
import com.example.springboottemplate.dao.InfoMapper;
import com.example.springboottemplate.service.InfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 信息表 服务实现类
 * </p>
 *
 * @author baochengkang
 * @since 2020-08-10
 */
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

}
