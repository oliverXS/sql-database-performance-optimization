package com.oliver.bean.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaorui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    /**
     * Represents the exception code.
     */
    protected String code;

    /**
     * Represents the response message.
     */
    private String message;

    /**
     * Represents the response data.
     */
    private Object data;
}
