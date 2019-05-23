package com.learn.service;

import com.learn.dto.CourseExperimentDTO;
import com.learn.pojo.Course;
import com.learn.pojo.CourseExperiment;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:33
 */

public interface CourseExperimentService {

    //查询所有courseExperiment
    public List<CourseExperiment> findAllcourseExperiment();

    //通过CourseId查询一门课程下所有实验信息表
    public List<CourseExperiment> findCourseExperimentByCourseId(String courseId);

    //通过courseId和userId查询一门课程下所有实验信息表和对应学生完成情况
    public List<CourseExperimentDTO> findCourseExperimentDTOWithUser(String courseId,String userId);

    //通过courExperimentId查询一个课程实验详细信息
    public CourseExperiment findCourseExperimentBycourExperimentId(String courExperimentId);

    //更新courseExperiment
    public void updateCourseExperiment(CourseExperiment courseExperiment);

    //根据courExperimentId导出一次实验下所有学生上交的实验
    public void courseExperimentExport(HttpServletResponse response, String courExperimentId);

    //查询最新添加进去的courExperimentId
    public Integer findMaxCourExperimentIdByCourseId(String courseId);

   //添加一条数据
    public void saveCourseExperiment(CourseExperiment courseExperiment);
}
