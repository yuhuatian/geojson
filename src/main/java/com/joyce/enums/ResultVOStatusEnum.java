package com.joyce.enums;

import lombok.Getter;

@Getter
public enum ResultVOStatusEnum {
    SUCCESS(0,"成功"),
        ERROR(1,"失败")
        ;
    private Integer code;
    private String msg;

        ResultVOStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        }
}
