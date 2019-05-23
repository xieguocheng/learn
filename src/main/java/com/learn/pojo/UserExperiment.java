package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "user_experiment")
public class UserExperiment {
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 对应的实验题目
     */
    @Column(name = "cour_experiment_id")
    private Integer courExperimentId;

    /**
     * 实验描述
     */
    private String desr;

    /**
     * 分数
     */
    private Double score;

    /**
     * 资源类型0-doc 1-ppt 2-png
     */
    private Integer type;

    /**
     * 上交实验文档url
     */
    @Column(name = "user_url")
    private String userUrl;

    /**
     * 教师评语
     */
    private String comment;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 实验上交时间
     */
    @Column(name = "up_time")
    private Date upTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0-未完成，1-完成
     */
    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
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
     * 获取对应的实验题目
     *
     * @return cour_experiment_id - 对应的实验题目
     */
    public Integer getCourExperimentId() {
        return courExperimentId;
    }

    /**
     * 设置对应的实验题目
     *
     * @param courExperimentId 对应的实验题目
     */
    public void setCourExperimentId(Integer courExperimentId) {
        this.courExperimentId = courExperimentId;
    }

    /**
     * 获取实验描述
     *
     * @return desr - 实验描述
     */
    public String getDesr() {
        return desr;
    }

    /**
     * 设置实验描述
     *
     * @param desr 实验描述
     */
    public void setDesr(String desr) {
        this.desr = desr;
    }

    /**
     * 获取分数
     *
     * @return score - 分数
     */
    public Double getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(Double score) {
        this.score = score;
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
     * 获取上交实验文档url
     *
     * @return user_url - 上交实验文档url
     */
    public String getUserUrl() {
        return userUrl;
    }

    /**
     * 设置上交实验文档url
     *
     * @param userUrl 上交实验文档url
     */
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
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
     * 获取实验上交时间
     *
     * @return up_time - 实验上交时间
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * 设置实验上交时间
     *
     * @param upTime 实验上交时间
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
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
     * 获取0-未完成，1-完成
     *
     * @return status - 0-未完成，1-完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-未完成，1-完成
     *
     * @param status 0-未完成，1-完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}