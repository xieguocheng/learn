package com.learn.dto;

import com.learn.pojo.User;
import com.learn.pojo.UserExperiment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/17 18:36
 */

@Data
public class CourseExperimentDTO {

    private Integer courExperimentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 实验标题
     */
    private String title;

    /**
     * 任务详情描述
     */
    private String detail;

    /**
     * 资源类型0-doc 1-ppt 2-png
     */
    private Integer type;

    /**
     * 实验资源url（存放在七牛云）
     */
    private String courUrl;

    /**
     * 实验设置分值（教师设置）
     */
    private Double score;

    /**
     * 允许超时间提交0-不允许 1-允许
     */
    private Integer overSubmit;

    /**
     * 状态0-已超时，1-进行中
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 截至时间
     */
    private Date endTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    //添加字段，用户查询每个用户对应experiment完成情况，user通过session中获取
    private UserExperiment userExperiment;




}
