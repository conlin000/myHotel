package com.conlin.response;

public enum ResponseStatus {
    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    ERROR(-1);

    int code;

    ResponseStatus(int code ){
        this.code = code;
    }
}
