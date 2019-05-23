package com.learn.enums;

import org.springframework.scripting.support.StaticScriptSource;

public enum UserExperimentStatus {

    TYPE_DOC(0),//上次类型：.doc
    TYPE_PPT(1),//上次类型：.ppt
    TYPE_PNG(2),//上次类型：.png

    STATUS_NO(0),//未提交 默认-0
    STATUS_YES(1),//已经提交了

    ;//

    private int value;

    UserExperimentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
