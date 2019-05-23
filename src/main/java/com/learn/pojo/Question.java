package com.learn.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
public class Question {
    @Id
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_type")
    private Integer questionType;

    private Integer status;

    @Column(name = "question_source")
    private String questionSource;

    @Column(name = "question_list_id")
    private Integer questionListId;

    @Column(name = "question_grade")
    private Integer questionGrade;

    private String answer;

    private Integer type;

    @Column(name = "question_desc")
    private String questionDesc;

    private String choice;

    /**
     * @return question_id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return question_type
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * @param questionType
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return question_source
     */
    public String getQuestionSource() {
        return questionSource;
    }

    /**
     * @param questionSource
     */
    public void setQuestionSource(String questionSource) {
        this.questionSource = questionSource;
    }

    /**
     * @return question_list_id
     */
    public Integer getQuestionListId() {
        return questionListId;
    }

    /**
     * @param questionListId
     */
    public void setQuestionListId(Integer questionListId) {
        this.questionListId = questionListId;
    }

    /**
     * @return question_grade
     */
    public Integer getQuestionGrade() {
        return questionGrade;
    }

    /**
     * @param questionGrade
     */
    public void setQuestionGrade(Integer questionGrade) {
        this.questionGrade = questionGrade;
    }

    /**
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return question_desc
     */
    public String getQuestionDesc() {
        return questionDesc;
    }

    /**
     * @param questionDesc
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    /**
     * @return choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * @param choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }
}