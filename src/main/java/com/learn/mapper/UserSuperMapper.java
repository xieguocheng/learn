package com.learn.mapper;

import com.learn.pojo.UserSuper;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSuperMapper extends MyMapper<UserSuper> {
}