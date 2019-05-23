package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "course_experiment")
public class CourseExperiment {
    @Id
    @Column(name = "cour_experiment_id")
    private Integer courExperimentId;

    /**
     * 课程id
     */
    @Column(name = "course_id")
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
    @Column(name = "cour_url")
    private String courUrl;

    /**
     * 实验设置分值（教师设置）
     */
    private Double score;

    /**
     * 允许超时间提交0-不允许 1-允许
     */
    @Column(name = "over_submit")
    private Integer overSubmit;

    /**
     * 状态0-已超时，1-进行中
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
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return cour_experiment_id
     */
    public Integer getCourExperimentId() {
        return courExperimentId;
    }

    /**
     * @param courExperimentId
     */
    public void setCourExperimentId(Integer courExperimentId) {
        this.courExperimentId = courExperimentId;
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
     * 获取实验标题
     *
     * @return title - 实验标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置实验标题
     *
     * @param title 实验标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取任务详情描述
     *
     * @return detail - 任务详情描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置任务详情描述
     *
     * @param detail 任务详情描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取资源类型0-doc 1-ppt 2-png
     *
     * @return type - 资源类型0-doc 1-ppt 2-png
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置资源类型0-doc 1-ppt 2-png
     *
     * @param type 资源类型0-doc 1-ppt 2-png
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取实验资源url（存放在七牛云）
     *
     * @return cour_url - 实验资源url（存放在七牛云）
     */
    public String getCourUrl() {
        return courUrl;
    }

    /**
     * 设置实验资源url（存放在七牛云）
     *
     * @param courUrl 实验资源url（存放在七牛云）
     */
    public void setCourUrl(String courUrl) {
        this.courUrl = courUrl;
    }

    /**
     * 获取实验设置分值（教师设置）
     *
     * @return score - 实验设置分值（教师设置）
     */
    public Double getScore() {
        return score;
    }

    /**
     * 设置实验设置分值（教师设置）
     *
     * @param score 实验设置分值（教师设置）
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 获取允许超时间提交0-不允许 1-允许
     *
     * @return over_submit - 允许超时间提交0-不允许 1-允许
     */
    public Integer getOverSubmit() {
        return overSubmit;
    }

    /**
     * 设置允许超时间提交0-不允许 1-允许
     *
     * @param overSubmit 允许超时间提交0-不允许 1-允许
     */
    public void setOverSubmit(Integer overSubmit) {
        this.overSubmit = overSubmit;
    }

    /**
     * 获取状态0-已超时，1-进行中
     *
     * @return status - 状态0-已超时，1-进行中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0-已超时，1-进行中
     *
     * @param status 状态0-已超时，1-进行中
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
}