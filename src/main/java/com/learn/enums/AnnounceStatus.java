package com.learn.enums;



public enum AnnounceStatus {

    CANCEL_YES(0),//系统撤销发布
    CANCEL_NO(1),//系统正常发布，默认-1

    REMOVE_YES(0),//用户逻辑删除
    REMOVE_NO(1),//用户正常接收，默认-1

    SEE_YES(1),//用户已经阅读
    SEE_NO(0)//用户还没阅读，默认-0
    ;//

    private int value;

    AnnounceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
