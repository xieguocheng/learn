package com.learn.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@Table(name = "user_super") //继承userDetails才能使用springframework.security
public class UserSuper implements UserDetails {
    /**
     * 超级管理员id
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 性别"1"男"2"女
     */
    private Integer sex;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 简介
     */
    @Column(name = "user_intro")
    private String userIntro;

    /**
     * 图片
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 用户账号创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 上次登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 状态
     */
    private Integer status;

    /*******************************外加**********************************/
    //非数据库字段添加注解transient
    @Transient
    private List<GrantedAuthority> authorityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    /*******************************外加**********************************/



    /**
     * 获取超级管理员id
     *
     * @return id - 超级管理员id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置超级管理员id
     *
     * @param id 超级管理员id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return username - 姓名
     */
    public String getUsername() {
        return username;
    }


    /**
     * 设置姓名
     *
     * @param username 姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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
     * 获取性别"1"男"2"女
     *
     * @return sex - 性别"1"男"2"女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别"1"男"2"女
     *
     * @param sex 性别"1"男"2"女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取简介
     *
     * @return user_intro - 简介
     */
    public String getUserIntro() {
        return userIntro;
    }

    /**
     * 设置简介
     *
     * @param userIntro 简介
     */
    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    /**
     * 获取图片
     *
     * @return user_img - 图片
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置图片
     *
     * @param userImg 图片
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取用户账号创建时间
     *
     * @return create_time - 用户账号创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置用户账号创建时间
     *
     * @param createTime 用户账号创建时间
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

    /**
     * 获取上次登录时间
     *
     * @return login_time - 上次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param loginTime 上次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


}