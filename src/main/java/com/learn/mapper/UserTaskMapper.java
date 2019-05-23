package com.learn.mapper;

import com.learn.pojo.UserTask;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTaskMapper extends MyMapper<UserTask> {
}