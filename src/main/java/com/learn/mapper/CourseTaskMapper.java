package com.learn.mapper;

import com.learn.pojo.CourseTask;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseTaskMapper extends MyMapper<CourseTask> {
}