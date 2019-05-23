package com.learn.mapper;

import com.learn.pojo.SystemLog;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemLogMapper extends MyMapper<SystemLog> {
}