package com.learn.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "course_type")
public class CourseType {
    /**
     * 课程分类
     */
    @Id
    @Column(name = "course_type")
    private Integer courseType;

    /**
     * 类名
     */
    private String name;

    /**
     * 获取课程分类
     *
     * @return course_type - 课程分类
     */
    public Integer getCourseType() {
        return courseType;
    }

    /**
     * 设置课程分类
     *
     * @param courseType 课程分类
     */
    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * 获取类名
     *
     * @return name - 类名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类名
     *
     * @param name 类名
     */
    public void setName(String name) {
        this.name = name;
    }
}