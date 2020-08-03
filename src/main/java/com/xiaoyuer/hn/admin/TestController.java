package com.xiaoyuer.hn.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    Userinfomapper userinfomapper;

    @RequestMapping("hnTest")
    @ResponseBody
    public String test(){


        System.out.println("ceshi ok");
        String username = userinfomapper.getuserInfobyid(1366);


        PageHelper.startPage(1, 5);
        List<UserInfo> getuserinfos = userinfomapper.getuserinfos();
        PageInfo gitPageInfo=new PageInfo(getuserinfos);

        System.out.println("ceshi end");
        System.out.println(username);

        return null;
    }

}
