package com.learn.enums;

public enum QuestionStatus {

    QUESTION_TYPE_SINGLE(1),//单选题
    QUESTION_TYPE_MULTIPLE(2),//多选题
    QUESTION_TYPE_FILLBLACK(3),//填空
    QUESTION_TYPE_ESSAY(4),//简答

    STATUS_DELETE(0),//题目不可用
    STATUS_NORMAL(1),//题目可以正常使用  默认-1
    STATUS_LOGIC_DELETE(2)//题目逻辑删除
    ;

    private int value;

    QuestionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
