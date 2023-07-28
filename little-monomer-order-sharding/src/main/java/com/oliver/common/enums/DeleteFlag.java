package com.oliver.common.enums;

/**
 * @author xiaorui
 */
public enum DeleteFlag {
    NOT_DELETE(0, "Not deleted"),
    DELETED(1, "Deleted"),
            ;
    public final Integer code;
    public final String desc;

    DeleteFlag(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
