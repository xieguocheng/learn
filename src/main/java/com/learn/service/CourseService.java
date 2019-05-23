package com.learn.service;

import com.learn.dto.CourseDTO;
import com.learn.pojo.Course;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 16:31
 */

public interface CourseService {

    //查询所有status-1进行中课程
    public List<CourseDTO> findAllCourseDoing();

    //查询所有status-2结束了的课程
    public List<CourseDTO> findAllCourseEnd();

    //查询所有status-0了删除了的课程
    public List<CourseDTO> findAllCourseDelete();

    //通过courseId查询一门课程基本信息
    public Course findCourseByCourseId(String courseId);

    //通过courseid查询一门课程,包含course，type，course_task，course_experiment,user_course,uer
    public CourseDTO findCourseDTOByCourseID(String courseId);

    //通过userid查询我创建所有的课程
    public List<Course> findCreateCourseByUserId(String userId);

    //通过userId查询我加入的所有课程
    //也就是通过userid查询userCourse下的所有courseid，在通过courseid查询我加入的所有课程
    public List<Course> findJoinCourseByUserId(String userId);

    //通过userid查询我创建所有的课程(包含教师信息）
    public List<CourseDTO> findCreateCourseWithTeacherByUserId(String userId);

    //通过userId查询我加入的所有课程(包含教师信息）
    //也就是通过userid查询userCourse下的所有courseid，在通过courseid查询我加入的所有课程
    public List<CourseDTO> findJoinCourseWithTeacherByUserId(String userId);

    //通过courseid查询一门课程,包含course，course_task,user_course(获取用户id)
    public CourseDTO findCourseDTOTaskByCourseId(String courseId);

    //通过courseid查询一门课程,包含course，course_experiment,user_course(获取用户id)
    public CourseDTO findCourseDTOExperimentByCourseId(String courseId);

    // 通过course更新课程
    public void updateCourse(Course course);

    //通过courseId逻辑删除课程,使status-0
    public void logicDeleteCourseByCourseId(String courseId);






}
