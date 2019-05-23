package com.learn.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
public class User  implements UserDetails {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
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
    @Column(name = "school_id")
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
    @Column(name = "user_img")
    private String userImg;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0-逻辑删除，1-未删除，默认为1
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

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

    /*******************************外加**********************************/
    //非数据库字段添加注解transient
    @Transient
    private List<GrantedAuthority> authorityList;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取手机号
     *
     * @return telephone - 手机号
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置手机号
     *
     * @param telephone 手机号
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * 获取用于绑定学号
     *
     * @return number - 用于绑定学号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置用于绑定学号
     *
     * @param number 用于绑定学号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取出生年月
     *
     * @return birthday - 出生年月
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年月
     *
     * @param birthday 出生年月
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取0为男，1为女,默认为0
     *
     * @return sex - 0为男，1为女,默认为0
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0为男，1为女,默认为0
     *
     * @param sex 0为男，1为女,默认为0
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取所在学校id
     *
     * @return school_id - 所在学校id
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * 设置所在学校id
     *
     * @param schoolId 所在学校id
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取所在院系名称
     *
     * @return dept - 所在院系名称
     */
    public String getDept() {
        return dept;
    }

    /**
     * 设置所在院系名称
     *
     * @param dept 所在院系名称
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * 获取0为学生，1为老师，2为其他，默认为0
     *
     * @return identity - 0为学生，1为老师，2为其他，默认为0
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * 设置0为学生，1为老师，2为其他，默认为0
     *
     * @param identity 0为学生，1为老师，2为其他，默认为0
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * 获取头像的url
     *
     * @return user_img - 头像的url
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置头像的url
     *
     * @param userImg 头像的url
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
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
     * 获取0-逻辑删除，1-未删除，默认为1
     *
     * @return delete_status - 0-逻辑删除，1-未删除，默认为1
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置0-逻辑删除，1-未删除，默认为1
     *
     * @param deleteStatus 0-逻辑删除，1-未删除，默认为1
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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
}