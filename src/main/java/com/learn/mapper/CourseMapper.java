package com.learn.mapper;

import com.learn.pojo.Course;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends MyMapper<Course> {


    //通过courseIds查询课程list
    List<Course> getCourseListByCourseIds(List<String> courseIds);
}