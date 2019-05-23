package com.learn.service;

import com.learn.dto.UserExperimentDTO;
import com.learn.pojo.UserExperiment;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/8 22:49
 */

public interface UserExperimentService {

    //通过一个courExperimentId查询一次实验下的所有UserExperimentDTO（包含UserExperiment，user信息）
    public List<UserExperimentDTO> findUserExperimentDTOByCourExperimentId(String courExperimentId);

    //通过userId和courExperimentId查询一次实验
    public UserExperiment findUserExperiment(String userId,Integer courExperimentId);

    //通过courExperimentId,courseId循环插入学生实验(courseId可以查出所有userId)
    public void addUserExperiment(Integer courExperimentId,String courseId);

    //跟新学生实验上交情况
    public void updateUserExperiment(UserExperiment userExperiment);
}
