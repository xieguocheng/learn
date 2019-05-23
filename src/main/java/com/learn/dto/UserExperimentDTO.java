package com.learn.dto;

import com.learn.pojo.User;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/6 22:14
 */

@Data
public class UserExperimentDTO {


    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 对应的实验题目
     */
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
    private String userUrl;

    /**
     * 教师评语
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 实验上交时间
     */
    private Date upTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0-未完成，1-完成
     */
    private Integer status;

    //添加字段属性User，表示同一个实验下所有学生实验
    private  User user;


}
