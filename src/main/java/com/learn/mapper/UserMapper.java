package com.learn.mapper;

import com.learn.pojo.User;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User> {

    //通过userIds查询用户list
    List<User> getUserListByUserIds(List<String> userIds);

}