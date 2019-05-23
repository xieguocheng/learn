package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "user_task")
public class UserTask {
    @Id
    @Column(name = "user_task_id")
    private Integer userTaskId;

    /**
     * userid
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * coursetaskid
     */
    @Column(name = "course_task_id")
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
    @Column(name = "answer_time")
    private Date answerTime;

    /**
     * @return user_task_id
     */
    public Integer getUserTaskId() {
        return userTaskId;
    }

    /**
     * @param userTaskId
     */
    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    /**
     * 获取userid
     *
     * @return user_id - userid
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置userid
     *
     * @param userId userid
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取coursetaskid
     *
     * @return course_task_id - coursetaskid
     */
    public Integer getCourseTaskId() {
        return courseTaskId;
    }

    /**
     * 设置coursetaskid
     *
     * @param courseTaskId coursetaskid
     */
    public void setCourseTaskId(Integer courseTaskId) {
        this.courseTaskId = courseTaskId;
    }

    /**
     * 获取答案
     *
     * @return answer - 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案
     *
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取0-未交 1-提交了
     *
     * @return status - 0-未交 1-提交了
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0-未交 1-提交了
     *
     * @param status 0-未交 1-提交了
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取得分
     *
     * @return grade - 得分
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * 设置得分
     *
     * @param grade 得分
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    /**
     * 获取答题时间
     *
     * @return answer_time - 答题时间
     */
    public Date getAnswerTime() {
        return answerTime;
    }

    /**
     * 设置答题时间
     *
     * @param answerTime 答题时间
     */
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }
}