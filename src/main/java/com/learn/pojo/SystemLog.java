package com.learn.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "system_log")
public class SystemLog {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 获取主机登录地址
     */
    private String address;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private String operateTime;

    /**
     * 状态1：在线
     */
    private Integer status;

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
     * 获取获取主机登录地址
     *
     * @return address - 获取主机登录地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置获取主机登录地址
     *
     * @param address 获取主机登录地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取别名
     *
     * @return nickname - 别名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置别名
     *
     * @param nickname 别名
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取操作时间
     *
     * @return operate_time - 操作时间
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取状态1：在线
     *
     * @return status - 状态1：在线
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态1：在线
     *
     * @param status 状态1：在线
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}