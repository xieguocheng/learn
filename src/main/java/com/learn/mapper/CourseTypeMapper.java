package com.learn.mapper;

import com.learn.pojo.CourseType;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseTypeMapper extends MyMapper<CourseType> {
}