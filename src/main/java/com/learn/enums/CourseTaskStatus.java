package com.learn.enums;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 22:36
 */

public enum  CourseTaskStatus {

    STATUS_NO(0),//未发布  默认-null
    STATUS_YES(1),//已经发布了
    STATUS_END(2)//已经截止
    ;

    private int value;

    CourseTaskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
