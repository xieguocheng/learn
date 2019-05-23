package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "user_course")
public class UserCourse {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 课程id
     */
    @Column(name = "course_id")
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
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取课程id
     *
     * @return course_id - 课程id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 设置课程id
     *
     * @param courseId 课程id
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取答案（用-连接）
     *
     * @return answer - 答案（用-连接）
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案（用-连接）
     *
     * @param answer 答案（用-连接）
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取做题分数（自动生成）
     *
     * @return grade - 做题分数（自动生成）
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * 设置做题分数（自动生成）
     *
     * @param grade 做题分数（自动生成）
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    /**
     * 获取教师评语
     *
     * @return comment - 教师评语
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置教师评语
     *
     * @param comment 教师评语
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取签到记录
     *
     * @return sign - 签到记录
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置签到记录
     *
     * @param sign 签到记录
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}