package com.learn.dto;

import com.learn.pojo.User;
import com.learn.pojo.UserTask;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/6 22:17
 */

@Data
public class UserTaskDTO {


    private Integer userTaskId;

    /**
     * userid
     */
    private String userId;

    /**
     * coursetaskid
     */
    private Integer courseTaskId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 0-未交 1-提交了
     */
    private String status;

    /**
     * 得分
     */
    private Double grade;

    /**
     * 答题时间
     */
    private Date answerTime;


    //添加字段属性userTask，表示一次作业下所有用户提交情况，用于饼状图分析
    // 优点：可用于查询所有userid，方便下面字段查询所有学生
    //private List<UserTask> userTaskList;

    //添加字段属性user，表示一次作业下一个用户信息
    //private List<User> userList;

    //添加字段属性user，表示一次作业下一个用户信息
    private  User user;




}
