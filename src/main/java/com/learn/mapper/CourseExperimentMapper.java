package com.learn.mapper;

import com.learn.pojo.CourseExperiment;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseExperimentMapper extends MyMapper<CourseExperiment> {


    //通过courseid查询最新插入的courExperimentId，也就是最大
    public Integer selectMaxCourExperimentIdByCourseId(String courseId);
}