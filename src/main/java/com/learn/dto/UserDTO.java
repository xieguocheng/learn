package com.learn.dto;

import com.learn.pojo.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 22:24
 */

@Data
public class UserDTO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;

    /**
     * 用于绑定学号
     */
    private String number;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 出生年月
     */
    private Date birthday;

    /**
     * 0为男，1为女,默认为0
     */
    private Integer sex;

    /**
     * 所在学校id
     */
    private Integer schoolId;

    /**
     * 所在院系名称
     */
    private String dept;

    /**
     * 0为学生，1为老师，2为其他，默认为0
     */
    private Integer identity;

    /**
     * 头像的url
     */
    private String userImg;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0-逻辑删除，1-未删除，默认为1
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    /**
     * 用户属性
     */
    //新加属性学校名称
    private School mySchool;
    //新加属性院系名称
    private Dept myDept;

    /**
     * 作为学生
     */
    //外加属性userTask集合，存储同一个课程所有作业集合(未使用）
    private List<UserTask> userTaskList;

    //外加属性userExperiment集合，存储同一个课程所有实验集合(未使用）
    private List<UserExperiment> userExperimentList;

    //外加属性UserCourse集合，存储用户加入所有课程信息（跟上面2个性质不同）(未使用）
    private List<UserCourse> userCourseList;














}
