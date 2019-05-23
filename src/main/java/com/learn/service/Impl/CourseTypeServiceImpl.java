package com.learn.service.Impl;

import com.learn.mapper.CourseTypeMapper;
import com.learn.pojo.CourseType;
import com.learn.service.CourseTypeService;
import com.learn.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/3 21:58
 */

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Override
    public List<CourseType> findAllCourseType() {
        return  courseTypeMapper.selectAll();

    }

    @Override
    public CourseType findCourseTypeById(Integer courseType) {
        return courseTypeMapper.selectByPrimaryKey(courseType);
    }
}
