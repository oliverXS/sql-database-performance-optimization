package com.oliver.bean.web;

import com.oliver.constant.ResponseCode;
import lombok.Data;

/**
 * @author xiaorui
 */
@Data
public class OperationResponse extends BaseResponse{
    /**
     * Response value
     */
    private String code;

    /**
     * Response message
     */
    private String message;

    /**
     * Returns a successful operation response object.
     *
     * @param message The message to be included in the response.
     * @return An OperationResponse representing a successful operation.
     * @author sunfeng
     */
    public static OperationResponse success(String message) {
        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setCode(ResponseCode.SUCCESS);
        operationResponse.setMessage(message);
        return operationResponse;
    }

    /**
     * Returns an error operation response object.
     *
     * @param message The message to be included in the response.
     * @return An OperationResponse representing an error operation.
     * @author sunfeng
     */
    public static OperationResponse error(String message) {
        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setCode(ResponseCode.ERROR);
        operationResponse.setMessage(message);
        return operationResponse;
    }

    /**
     * Returns a custom operation response object.
     *
     * @param code    The response value.
     * @param message The response message.
     * @return An OperationResponse representing a custom operation.
     * @author sunfeng
     */
    public static OperationResponse build(String code, String message) {
        OperationResponse operationResponse = new OperationResponse();
        operationResponse.setCode(code);
        operationResponse.setMessage(message);
        return operationResponse;
    }

}
