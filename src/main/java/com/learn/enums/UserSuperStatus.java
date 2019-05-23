package com.learn.enums;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/4 18:04
 */

public enum  UserSuperStatus {

    STATUS_NO(0),//停用超级管理员
    STATUS_YES(1),//正常超级管理员 默认-1
    STATUS_SUPER(2)//最超级的管理员
    ;//

    private int value;

    UserSuperStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
