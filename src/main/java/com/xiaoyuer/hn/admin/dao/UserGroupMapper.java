package com.xiaoyuer.hn.admin.dao;

import com.xiaoyuer.hn.admin.dmo.UserGroup;

import java.util.List;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);

    List<UserGroup> testGetAll();
}