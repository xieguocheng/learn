package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.pojo.CourseType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author： XO
 * @Description： 实体类扩充属性，用户前端显示连表内容
 * @Date： 2019/4/3 20:14
 */

@Data
public class QuestionDTO implements Serializable {

    @JsonProperty("questionId")
    private Integer questionId;

    private Integer questionType;

    private Integer status;

    private String questionSource;

    private Integer questionListId;

    private Integer questionGrade;

    private String answer;

    private Integer type;

    private String questionDesc;

    private String choice;

    //新加属性
    private CourseType courseType;
}
