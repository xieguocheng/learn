package com.learn.service.Impl;

import com.learn.dto.UserDTO;
import com.learn.dto.UserTaskDTO;
import com.learn.mapper.UserTaskMapper;
import com.learn.pojo.CourseTask;
import com.learn.pojo.User;
import com.learn.pojo.UserTask;
import com.learn.service.UserService;
import com.learn.service.UserTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/7 9:35
 */

@Service
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    public UserTaskMapper userTaskMapper;

    @Autowired
    public UserService userService;

    @Autowired
    public ModelMapper modelMapper;


    @Override
    public List<UserTaskDTO> findUserTaskDTOByCourseTaskId(String courseTaskId) {
        //定义存放具体UserTaskDTO,(包含user信息)
        List<UserTaskDTO> userTaskDTOList=new ArrayList<UserTaskDTO>();
        //1.查询所有userTask
        Example courseTaskExample=new Example(CourseTask.class);
        courseTaskExample.createCriteria().andEqualTo("courseTaskId",courseTaskId);
        List<UserTask> userTaskList=userTaskMapper.selectByExample(courseTaskExample);
        //2.将userTask对象封装进userTaskDTO中
        userTaskList.forEach(userTask -> {
            UserTaskDTO userTaskDTO=modelMapper.map(userTask,UserTaskDTO.class);
            //3.将查询user对象也封装进userTaskDTO中
            userTaskDTO.setUser(userService.findUserByUserId(userTask.getUserId()));
            userTaskDTOList.add(userTaskDTO);
        });
        return userTaskDTOList;
    }
}
