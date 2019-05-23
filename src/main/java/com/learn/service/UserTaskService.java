package com.learn.service;


import com.learn.dto.UserTaskDTO;
import com.learn.pojo.CourseTask;
import com.learn.pojo.UserTask;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/7 9:29
 */

public interface UserTaskService {


    //通过courseTaskid集合查询一门课程作业的所有userTask,也就是查询一门课程下面的所有作业
    //public List<UserTask> findUserTaskByCourseTask(List<String> courseTaskId);

    //通过一个courseTaskId查询一次作业下的所有UserTaskDTO（包含userTask，user信息）
    public List<UserTaskDTO> findUserTaskDTOByCourseTaskId(String courseTaskId);





}
