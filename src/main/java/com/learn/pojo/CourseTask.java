package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "course_task")
public class CourseTask {
    /**
     * cour_task_id
     */
    @Id
    @Column(name = "course_task_id")
    private Integer courseTaskId;

    /**
     * courseid
     */
    @Column(name = "course_id")
    private String courseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 题目列表
     */
    @Column(name = "question_list_id")
    private String questionListId;

    /**
     * 总分
     */
    @Column(name = "total_grade")
    private Double totalGrade;

    /**
     * 0-发布1-未发布
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 截至时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 获取cour_task_id
     *
     * @return course_task_id - cour_task_id
     */
    public Integer getCourseTaskId() {
        return courseTaskId;
    }

    /**
     * 设置cour_task_id
     *
     * @param courseTaskId cour_task_id
     */
    public void setCourseTaskId(Integer courseTaskId) {
        this.courseTaskId = courseTaskId;
    }

    /**
     * 获取courseid
     *
     * @return course_id - courseid
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 设置courseid
     *
     * @param courseId courseid
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取题目列表
     *
     * @return question_list_id - 题目列表
     */
    public String getQuestionListId() {
        return questionListId;
    }

    /**
     * 设置题目列表
     *
     * @param questionListId 题目列表
     */
    public void setQuestionListId(String questionListId) {
        this.questionListId = questionListId;
    }

    /**
     * 获取总分
     *
     * @return total_grade - 总分
     */
    public Double getTotalGrade() {
        return totalGrade;
    }

    /**
     * 设置总分
     *
     * @param totalGrade 总分
     */
    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

    /**
     * 获取0-发布1-未发布
     *
     * @return status - 0-发布1-未发布
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-发布1-未发布
     *
     * @param status 0-发布1-未发布
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
     * 获取截至时间
     *
     * @return end_time - 截至时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置截至时间
     *
     * @param endTime 截至时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}