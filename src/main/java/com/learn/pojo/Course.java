package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
public class Course {
    /**
     * 课程ID
     */
    @Id
    @Column(name = "course_id")
    private String courseId;

    /**
     * 任课老师ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 课程名称
     */
    @Column(name = "course_name")
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
    @Column(name = "course_type")
    private Integer courseType;

    /**
     * 课程封面
     */
    @Column(name = "course_img")
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
    @Column(name = "question_list_id")
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
     * 0为逻辑删除，1为进行中的课程，2为结束了的课程
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 结束时间,没有用了的字段
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 课程简介
     */
    private String introduction;

    /**
     * 获取课程ID
     *
     * @return course_id - 课程ID
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 设置课程ID
     *
     * @param courseId 课程ID
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取任课老师ID
     *
     * @return user_id - 任课老师ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置任课老师ID
     *
     * @param userId 任课老师ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取课程名称
     *
     * @return course_name - 课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置课程名称
     *
     * @param courseName 课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 获取每门课程对应一个编号
     *
     * @return classnum - 每门课程对应一个编号
     */
    public String getClassnum() {
        return classnum;
    }

    /**
     * 设置每门课程对应一个编号
     *
     * @param classnum 每门课程对应一个编号
     */
    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    /**
     * 获取允许加入0-不允许 1-允许
     *
     * @return added - 允许加入0-不允许 1-允许
     */
    public Integer getAdded() {
        return added;
    }

    /**
     * 设置允许加入0-不允许 1-允许
     *
     * @param added 允许加入0-不允许 1-允许
     */
    public void setAdded(Integer added) {
        this.added = added;
    }

    /**
     * 获取课程类型(学科分类)
     *
     * @return course_type - 课程类型(学科分类)
     */
    public Integer getCourseType() {
        return courseType;
    }

    /**
     * 设置课程类型(学科分类)
     *
     * @param courseType 课程类型(学科分类)
     */
    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * 获取课程封面
     *
     * @return course_img - 课程封面
     */
    public String getCourseImg() {
        return courseImg;
    }

    /**
     * 设置课程封面
     *
     * @param courseImg 课程封面
     */
    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    /**
     * 获取学习要求
     *
     * @return request - 学习要求
     */
    public String getRequest() {
        return request;
    }

    /**
     * 设置学习要求
     *
     * @param request 学习要求
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * 获取课程所在班级名称（由老师描述）
     *
     * @return classname - 课程所在班级名称（由老师描述）
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 设置课程所在班级名称（由老师描述）
     *
     * @param classname 课程所在班级名称（由老师描述）
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * 获取所属学期
     *
     * @return semeter - 所属学期
     */
    public String getSemeter() {
        return semeter;
    }

    /**
     * 设置所属学期
     *
     * @param semeter 所属学期
     */
    public void setSemeter(String semeter) {
        this.semeter = semeter;
    }

    /**
     * 获取考试安排
     *
     * @return examin - 考试安排
     */
    public String getExamin() {
        return examin;
    }

    /**
     * 设置考试安排
     *
     * @param examin 考试安排
     */
    public void setExamin(String examin) {
        this.examin = examin;
    }

    /**
     * 获取每个习题编号中间以","分隔
     *
     * @return question_list_id - 每个习题编号中间以","分隔
     */
    public String getQuestionListId() {
        return questionListId;
    }

    /**
     * 设置每个习题编号中间以","分隔
     *
     * @param questionListId 每个习题编号中间以","分隔
     */
    public void setQuestionListId(String questionListId) {
        this.questionListId = questionListId;
    }

    /**
     * 获取课程进度
     *
     * @return speed - 课程进度
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * 设置课程进度
     *
     * @param speed 课程进度
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     * 获取签到情况
     *
     * @return sign - 签到情况
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置签到情况
     *
     * @param sign 签到情况
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取每门课程对应的班级人数
     *
     * @return people - 每门课程对应的班级人数
     */
    public Integer getPeople() {
        return people;
    }

    /**
     * 设置每门课程对应的班级人数
     *
     * @param people 每门课程对应的班级人数
     */
    public void setPeople(Integer people) {
        this.people = people;
    }

    /**
     * 获取0为逻辑删除，1为未删除
     *
     * @return status - 0为逻辑删除，1为未删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0为逻辑删除，1为未删除
     *
     * @param status 0为逻辑删除，1为未删除
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取课程简介
     *
     * @return introduction - 课程简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置课程简介
     *
     * @param introduction 课程简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}