package com.learn.service.Impl;

import com.learn.dto.UserCourseDTO;
import com.learn.mapper.UserCourseMapper;
import com.learn.mapper.UserMapper;
import com.learn.pojo.User;
import com.learn.pojo.UserCourse;
import com.learn.service.UserCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:43
 */

@Service
public class UserCourseServiceImpl implements UserCourseService {


    @Autowired
    public UserCourseMapper userCourseMapper;

    @Autowired
    public UserMapper userMapper;

    //可以用于属性复制
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<UserCourse> findUserCourseByCourseId(String courseId) {
        Example userCourseExample = new Example(UserCourse.class);
        userCourseExample.createCriteria().andEqualTo("courseId", courseId);
        List<UserCourse> userCourseList = userCourseMapper.selectByExample(userCourseExample);
        //如果查询不到直接返回一个空对象集合
        if(userCourseList.size()==0){
            return new ArrayList<UserCourse>();
        }
        return userCourseList;
    }

    @Override
    public List<UserCourse> findUserCourseByUserId(String userId) {
        Example userCourseExample = new Example(UserCourse.class);
        userCourseExample.createCriteria().andEqualTo("userId", userId);
        List<UserCourse> userCourseList = userCourseMapper.selectByExample(userCourseExample);
        //如果查询不到直接返回一个空对象集合
        if(userCourseList.size()==0){
            return new ArrayList<UserCourse>();
        }
        return userCourseList;
    }

    @Override
    public List<UserCourseDTO> findUserCourseDTOByCourseId(String courseId) {
        Example userCourseExample = new Example(UserCourse.class);
        userCourseExample.createCriteria().andEqualTo("courseId", courseId);
        List<UserCourse> userCourseList = userCourseMapper.selectByExample(userCourseExample);
        //如果查询不到直接返回一个空对象集合
        if(userCourseList.size()==0){
            return new ArrayList<UserCourseDTO>();
        }
        //进行查询
        List<UserCourseDTO> userCourseDTOList=new ArrayList<UserCourseDTO>();
        userCourseList.forEach(userCourse -> {
            UserCourseDTO userCourseDTO=modelMapper.map(userCourse,UserCourseDTO.class);
            userCourseDTO.setStudent(userMapper.selectByPrimaryKey(userCourse.getUserId()));
            userCourseDTOList.add(userCourseDTO);
        });
        return userCourseDTOList;

    }





}
