package com.learn.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.pojo.CourseType;
import lombok.Data;

/**
 * @Author： XO
 * @Description： 前台提交的表达验证
 * @Date： 2019/4/4 11:18
 */

@Data
public class QuestionFrom {

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
