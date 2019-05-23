package com.learn.service.Impl;

import com.learn.dto.UserExperimentDTO;
import com.learn.mapper.UserExperimentMapper;
import com.learn.pojo.UserCourse;
import com.learn.pojo.UserExperiment;
import com.learn.service.CourseExperimentService;
import com.learn.service.UserCourseService;
import com.learn.service.UserExperimentService;
import com.learn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/8 22:51
 */

@Service
public class UserExperimentServiceImpl implements UserExperimentService {

    @Autowired
    public UserExperimentMapper userExperimentMapper;

    @Autowired
    public UserService userService;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserCourseService userCourseService;




    @Override
    public List<UserExperimentDTO> findUserExperimentDTOByCourExperimentId(String courExperimentId) {
        //定义存放具体UserExperimentDTO,(包含user信息)
        List<UserExperimentDTO> userExperimentDTOList=new ArrayList<UserExperimentDTO>();
        //1.查询所有UserExperiment
        Example userExperimentExample=new Example(UserExperiment.class);
        userExperimentExample.createCriteria().andEqualTo("courExperimentId",courExperimentId);
        List<UserExperiment> userExperimentList=userExperimentMapper.selectByExample(userExperimentExample);
        //2.将userTask对象封装进userTaskDTO中
        userExperimentList.forEach(userExperiment -> {
            UserExperimentDTO userExperimentDTO=modelMapper.map(userExperiment,UserExperimentDTO.class);
            //3.将查询user对象也封装进UserExperimentDTO中
            userExperimentDTO.setUser(userService.findUserByUserId(userExperiment.getUserId()));
            userExperimentDTOList.add(userExperimentDTO);
        });
        return userExperimentDTOList;

    }

    @Override
    public UserExperiment findUserExperiment(String userId, Integer courExperimentId) {
        //1.查询所有UserExperiment
        Example userExperimentExample=new Example(UserExperiment.class);
        userExperimentExample.createCriteria().
                andEqualTo("courExperimentId",courExperimentId).
                andEqualTo("userId",userId);
        List<UserExperiment> userExperimentList=userExperimentMapper.selectByExample(userExperimentExample);
        if(userExperimentList.size()==0){
            return null;
        }
        return userExperimentList.get(0);
    }

    @Override
    public void addUserExperiment(Integer courExperimentId, String courseId) {
        List<UserCourse> userCourseList=userCourseService.findUserCourseByCourseId(courseId);
        userCourseList.forEach(userCourse -> {
            //循环插入
            UserExperiment userExperiment=new UserExperiment();
            userExperiment.setCourExperimentId(courExperimentId);
            userExperiment.setUserId(userCourse.getUserId());
            userExperimentMapper.insertSelective(userExperiment);
        });

    }

    @Override
    public void updateUserExperiment(UserExperiment userExperiment) {
        Example userExperimentExample=new Example(UserExperiment.class);
        userExperimentExample.createCriteria().
                andEqualTo("courExperimentId",userExperiment.getCourExperimentId()).
                andEqualTo("userId",userExperiment.getUserId());
        userExperimentMapper.updateByExampleSelective(userExperiment,userExperimentExample);
    }


}
