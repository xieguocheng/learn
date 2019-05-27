package com.learn.enums;

public enum CourseStatus {


    ADDED_YES(1),//可以加入课程 默认-0
    ADDED_NO(0),//不能加入课程


    STATUS_ADMIN_DELETE(0),//管理员逻辑删除课程/用户删除了课程
    STATUS_NORMAL(1),//正常进行中课程  默认-1
    STATUS_END(2),//结束了的课程
    STATUS_USER_DELETE(3)//用户删除了课程，（小程序暂时没有这个功能）
    ;//

    private int value;

    CourseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
