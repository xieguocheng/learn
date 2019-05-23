package com.learn.enums;

public enum UserTaskStatus {

    STATUS_NO("0"),//未提交 默认-0
    STATUS_YES("1");//已经提交了

    private String value;

    UserTaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
