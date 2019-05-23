package com.learn.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
public class Announce {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 管理员是否撤销0-撤销发布 1-已经发布
     */
    private Integer cancel;

    /**
     * 撤销时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 用户是否删除0-逻辑删除 1-正常接收
     */
    private Integer remove;

    /**
     * 用户删除时间
     */
    @Column(name = "remove_time")
    private Date removeTime;

    /**
     * 优先级高的信息放前面
     */
    private Integer priority;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户是否阅读过了0-未阅读 1-已经阅读
     */
    private Integer see;

    /**
     * 阅读时间
     */
    @Column(name = "see_time")
    private Date seeTime;

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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发布时间
     *
     * @return start_time - 发布时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置发布时间
     *
     * @param startTime 发布时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取管理员是否撤销0-撤销发布 1-已经发布
     *
     * @return cancel - 管理员是否撤销0-撤销发布 1-已经发布
     */
    public Integer getCancel() {
        return cancel;
    }

    /**
     * 设置管理员是否撤销0-撤销发布 1-已经发布
     *
     * @param cancel 管理员是否撤销0-撤销发布 1-已经发布
     */
    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    /**
     * 获取撤销时间
     *
     * @return cancel_time - 撤销时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 设置撤销时间
     *
     * @param cancelTime 撤销时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 获取用户是否删除0-逻辑删除 1-正常接收
     *
     * @return remove - 用户是否删除0-逻辑删除 1-正常接收
     */
    public Integer getRemove() {
        return remove;
    }

    /**
     * 设置用户是否删除0-逻辑删除 1-正常接收
     *
     * @param remove 用户是否删除0-逻辑删除 1-正常接收
     */
    public void setRemove(Integer remove) {
        this.remove = remove;
    }

    /**
     * 获取用户删除时间
     *
     * @return remove_time - 用户删除时间
     */
    public Date getRemoveTime() {
        return removeTime;
    }

    /**
     * 设置用户删除时间
     *
     * @param removeTime 用户删除时间
     */
    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    /**
     * 获取优先级高的信息放前面
     *
     * @return priority - 优先级高的信息放前面
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级高的信息放前面
     *
     * @param priority 优先级高的信息放前面
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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
     * 获取用户是否阅读过了0-未阅读 1-已经阅读
     *
     * @return see - 用户是否阅读过了0-未阅读 1-已经阅读
     */
    public Integer getSee() {
        return see;
    }

    /**
     * 设置用户是否阅读过了0-未阅读 1-已经阅读
     *
     * @param see 用户是否阅读过了0-未阅读 1-已经阅读
     */
    public void setSee(Integer see) {
        this.see = see;
    }

    /**
     * 获取阅读时间
     *
     * @return see_time - 阅读时间
     */
    public Date getSeeTime() {
        return seeTime;
    }

    /**
     * 设置阅读时间
     *
     * @param seeTime 阅读时间
     */
    public void setSeeTime(Date seeTime) {
        this.seeTime = seeTime;
    }
}