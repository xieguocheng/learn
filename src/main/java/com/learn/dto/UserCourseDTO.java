package com.learn.dto;

import com.learn.pojo.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/18 20:59
 */

@Data
public class UserCourseDTO {

    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 答案（用-连接）
     */
    private String answer;

    /**
     * 做题分数（自动生成）
     */
    private Double grade;

    /**
     * 教师评语
     */
    private String comment;

    /**
     * 签到记录
     */
    private String sign;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    //新增字段，表示一门课程下对应的学生
    private User student;






}
