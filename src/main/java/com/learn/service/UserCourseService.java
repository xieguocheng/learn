package com.learn.service;

import com.learn.dto.UserCourseDTO;
import com.learn.pojo.UserCourse;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:34
 */

public interface UserCourseService {

    //通过CourseId查询课程用户信息表
    public List<UserCourse> findUserCourseByCourseId(String courseId);

    //通过userId查询用户课程信息表
    public List<UserCourse> findUserCourseByUserId(String userId);

    //通过courseId查询一门课程下的所有学生，返回usercourseDTO包含学生集合
    public List<UserCourseDTO> findUserCourseDTOByCourseId(String courseId);


}
