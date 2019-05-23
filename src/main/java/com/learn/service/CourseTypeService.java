package com.learn.service;

import com.learn.pojo.CourseType;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/3 21:57
 */

public interface CourseTypeService {

    //查询所有课程题目分类
    public List<CourseType> findAllCourseType();

    //通过课程分类id查询课程类别
    public CourseType findCourseTypeById(Integer courseType);

}
