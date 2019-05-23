package com.learn.enums;

public enum CourseExperimentStatus {

    TYPE_DOC(0),//上次类型：.doc
    TYPE_PPT(1),//上次类型：.ppt
    TYPE_PNG(2),//上次类型：.png

    OVER_SUBMIT_NO(0),//不允许超时提交
    OVER_SUBMIT_YES(1),//允许超时间提交，默认-1

    //STATUS_NO(0),//未发布          默认-0
    STATUS_YES(0),//已经截止的实验
    STATUS_END(1)//进行中的实验
    ;

    private int value;

    CourseExperimentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
