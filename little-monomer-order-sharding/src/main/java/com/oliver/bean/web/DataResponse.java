package com.oliver.bean.web;

import com.oliver.constant.ResponseCode;
import lombok.Data;

/**
 * @author xiaorui
 */
@Data
public class DataResponse extends BaseResponse{
    /**
     * Response value
     */
    private String code;

    /**
     * Query object
     */
    private Object data;

    /**
     * Response message
     */
    private String message;

    /**
     * Returns a successful data query response object.
     *
     * @param data The data object.
     * @return A DataResponse representing a successful data query operation.
     */
    public static DataResponse success(Object data) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(ResponseCode.SUCCESS);
        dataResponse.setData(data);
        return dataResponse;
    }

    /**
     * Returns a successful data query response object with a custom response code.
     *
     * @param data The data object.
     * @param code The response value.
     * @return A DataResponse representing a successful data query operation with a custom response code.
     */
    public static DataResponse success(Object data, String code) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(code);
        dataResponse.setData(data);
        return dataResponse;
    }

    /**
     * Returns an error data query response object.
     *
     * @return A DataResponse representing an error data query operation.
     */
    public static DataResponse error() {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(ResponseCode.ERROR);
        return dataResponse;
    }

    /**
     * Returns an error data query response object with a custom message.
     *
     * @param message The message to be included in the response.
     * @return A DataResponse representing an error data query operation with a custom message.
     */
    public static DataResponse error(String message) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(ResponseCode.ERROR);
        dataResponse.setMessage(message);
        return dataResponse;
    }

    /**
     * Returns an error data query response object with a custom message and code.
     *
     * @param message The message to be included in the response.
     * @param code    The response value.
     * @return A DataResponse representing an error data query operation with a custom message and code.
     */
    public static DataResponse error(String message, String code) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(code);
        dataResponse.setMessage(message);
        return dataResponse;
    }

    /**
     * Returns a custom data query response object.
     *
     * @param code The response value.
     * @param data The query object.
     * @return A DataResponse representing a custom data query operation.
     */
    public static DataResponse build(String code, Object data) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(code);
        dataResponse.setData(data);
        return dataResponse;
    }
}
