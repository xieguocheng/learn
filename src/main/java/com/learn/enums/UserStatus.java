package com.learn.enums;

public enum UserStatus {

    DELETESTATUS_NO(0),//逻辑删除
    DELETESTATUS_YES(1);//正常 默认-1

    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
