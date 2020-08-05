package com.xiaoyuer.hn.admin.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Userinfomapper {


    @Select("select user_name from user_base_info where id=#{id}")
    String getuserInfobyid(Integer id);


    @Select("select * from user_base_info where 1=1 and id>1625")
    @Results({@Result(column = "user_name",property = "userName"),
            @Result(column = "cell_phone",property = "cellPhone"),
            @Result(column = "nick_name",property = "nickName")
            })
    List<UserInfo> getuserinfos();

}
