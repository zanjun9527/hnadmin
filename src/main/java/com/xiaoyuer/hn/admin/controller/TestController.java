package com.xiaoyuer.hn.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyuer.hn.admin.Util.RedisUtil;
import com.xiaoyuer.hn.admin.dao.UserGroupMapper;
import com.xiaoyuer.hn.admin.dmo.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    StringRedisTemplate  stringRedisTemplate;

    @Autowired
    UserGroupMapper userGroupMapper;

    @RequestMapping("hnTest")
    @ResponseBody
    public String test(){


        System.out.println("ceshi ok");
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("默认分组");
        userGroup.setUserId(123);
        userGroup.setDateInsert(new Date());
        userGroupMapper.insertSelective(userGroup);

        PageHelper.startPage(1, 6);
        List<UserGroup> result = userGroupMapper.testGetAll();
        PageInfo gitPageInfo=new PageInfo(result);

        redisUtil.set("ak47","boom");


        System.out.println("123");

        return null;
    }

}
