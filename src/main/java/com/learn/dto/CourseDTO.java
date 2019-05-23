package com.learn.dto;

import com.learn.pojo.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description： 实体类扩充属性，用户前端显示连表内容
 * @Date： 2019/4/5 16:33
 */
@Data
public class CourseDTO {


    /**
     * 课程ID
     */
    @Id
    private String courseId;

    /**
     * 任课老师ID
     */
    private String userId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 每门课程对应一个编号
     */
    private String classnum;

    /**
     * 允许加入0-不允许 1-允许
     */
    private Integer added;

    /**
     * 课程类型(学科分类)
     */
    private Integer courseType;

    /**
     * 课程封面
     */
    private String courseImg;

    /**
     * 学习要求
     */
    private String request;

    /**
     * 课程所在班级名称（由老师描述）
     */
    private String classname;

    /**
     * 所属学期
     */
    private String semeter;

    /**
     * 考试安排
     */
    private String examin;

    /**
     * 每个习题编号中间以","分隔
     */
    private String questionListId;

    /**
     * 课程进度
     */
    private String speed;

    /**
     * 签到情况
     */
    private String sign;

    /**
     * 每门课程对应的班级人数
     */
    private Integer people;

    /**
     * 0为逻辑删除，1为未删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 结束时间
     */
    private Date endTime=null;

    /**
     * 课程简介
     */
    private String introduction;

    //新加属性，课程用户userid（老师）
    private  User teacher;

    //新加属性，课程分类对象
    private CourseType myCourseType;

    //新增加属性，习题对象集合
    private List<CourseTask> courseTaskList;

    //新增加属性，实验对象集合
    private List<CourseExperiment> courseExperimentList;

    //新增加属性，用户（学生）对象集合，表示一门课程下面的所有学生
    private List<User> userList;

    //新增加属性，用户课程表，主要用来获取所有userid，方便上面userlist字段查询
    private List<UserCourse> userCourseList;


}
