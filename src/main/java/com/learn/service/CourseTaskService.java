package com.learn.service;

import com.learn.pojo.CourseTask;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:33
 */

public interface CourseTaskService {

    //查询所有CourseTaskService
    public List<CourseTask> findAllCourseTask();

    //通过CourseId查询所有课程作业
    public List<CourseTask> findCourseTaskByCourseId(String courseId);

    //通过courseTaskid查询一门课程
    public CourseTask findCourseTaskByCourseTaskId(String courseTaskId);

    //通过CourseTask更新
    public void updateCourseTask(CourseTask courseTask);

}
