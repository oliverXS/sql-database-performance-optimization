package com.oliver.common.enums;

/**
 * @author xiaorui
 */
public enum OrderCode {
    ADD_ORDER_SUCCESS("100","Order added successfully"),
    ADD_ORDER_ERROR("200", "Failed to add order"),
    UPDATE_ORDER_SUCCESS("300","Order modified successfully"),
    UPDATE_ORDER_ERROR("400", "Failed to modify order"),
    DELETE_ORDER_SUCCESS("500","Order deleted successfully"),
    DELETE_ORDER_ERROR("600", "Failed to delete order"),
    QUERY_ORDER_ERROR("700", "Query order anomalies"),
    ;
    public final String code;
    public final String desc;

    OrderCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
