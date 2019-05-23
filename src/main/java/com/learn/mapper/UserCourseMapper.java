package com.learn.mapper;

import com.learn.pojo.UserCourse;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCourseMapper extends MyMapper<UserCourse> {
}