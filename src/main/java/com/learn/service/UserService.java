package com.learn.service;

import com.learn.dto.UserDTO;
import com.learn.pojo.User;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:34
 */

public interface UserService {


    //查询所有学生基本信息
    public List<User> findAllUser();

    //查询一个学生信息包括schoool，dept
    public UserDTO findUserDTOByUserId(String userId);

    //通过courseId查询一门课程下面的所有学生
    // 也就是通过CourseId查询usercourse下所有用户ids，在通过ids查询一门课程下面的所有学生
    public List<User> findUserByCourseId(String courseId);

    //通过userid查询用户
    public  User findUserByUserId(String userId);

    //通过username查询用户
    public User findUserByUsername(String username);

    //通过number学号查询用户
    public User findUserByNumber(String number);

}
